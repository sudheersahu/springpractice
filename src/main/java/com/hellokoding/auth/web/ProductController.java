package com.hellokoding.auth.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hellokoding.auth.model.Product;
import com.hellokoding.auth.service.ProductService;
import com.hellokoding.auth.vo.CartInfo;
import com.hellokoding.auth.vo.ProductInfo;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value ="/productList" , method = RequestMethod.GET)
	public String findAllProducts(Model model){
		
		model.addAttribute("productList", productService.findAllProducts());
		return "welcome";
		
	}
	
	@RequestMapping(value = "/buyProduct" , method = RequestMethod.GET)
	public String addProduct(HttpServletRequest request, Model model,@RequestParam(value = "code", defaultValue = "") String code ) {
		
		Product product = null;
		if (code != null && code.length() > 0) {
			product = productService.findByProductCode(code);
		}
		
		
		
		return "redirect:/shoppingCart";
		
	}
	
}
