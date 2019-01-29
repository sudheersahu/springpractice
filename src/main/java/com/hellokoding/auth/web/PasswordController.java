package com.hellokoding.auth.web;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hellokoding.auth.model.User;
import com.hellokoding.auth.service.EmailService;
import com.hellokoding.auth.service.UserService;



@Controller
public class PasswordController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	 @RequestMapping(value = "/forgot", method = RequestMethod.GET)
	 public ModelAndView displayForgotPasswordPage() {
			return new ModelAndView("forgotPassword");
	    }
	 
	 
	 @RequestMapping(value = "/forgot", method = RequestMethod.POST)
	  public ModelAndView  processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("emailId") String userEmail, HttpServletRequest request) {
	     
		 

            User user = userService.findByEmailId(userEmail);
            
            if (user == null) {
    			modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
    		} else {
             user.setResetToken(UUID.randomUUID().toString());
             
             userService.save(user);
             
             String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
             
             SimpleMailMessage passwordMail = new SimpleMailMessage();
             passwordMail.setFrom("sudheersahu5@gmail.com");
             passwordMail.setTo(user.getEmailId());
             passwordMail.setSubject("Password Reset Request");
             passwordMail.setText("To reset your password. click the link below :\n"+ appUrl + "/reset?token=" +user.getResetToken());
             emailService.sendEmails(passwordMail);
             modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
    		}

            modelAndView.setViewName("forgotPassword");
	        return modelAndView;
	    }
	 
	 @RequestMapping(value = "/reset",method = RequestMethod.GET)
	 public String displayResetPage( @RequestParam("token") String token,Model model) {
		 
		 User user = userService.findByResetToken(token);
		 
		 if(user == null) {
			 model.addAttribute("errorMessage", "Oops!  This is an invalid password reset link.");
		 } else {
			 model.addAttribute("resetToken", token);
		 }
		 
		
		return "resetPassword";
		 
	 }
	 
	 @RequestMapping(value = "/reset", method = RequestMethod.POST)
	 public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String,String> requestParams, RedirectAttributes redir) {
		  User user = userService.findByResetToken(requestParams.get("token"));
		  
		  if(user!=null) {
			  user.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
			  user.setResetToken(null);
			  userService.save(user);
			  redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");
			  modelAndView.setViewName("redirect : login");
			  return modelAndView;
		  }else {
			  modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
			  modelAndView.setViewName("resetPassword");	
		  }
		 
		 return modelAndView;
		 
	 }
	 
	 @ExceptionHandler(MissingServletRequestParameterException.class)
	 public ModelAndView handleMissingParams(MissingServletRequestParameterException exp) {
		return new ModelAndView("redirect : login");
		 
	 }
	 
}
