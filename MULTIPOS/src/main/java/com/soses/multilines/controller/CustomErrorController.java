package com.soses.multilines.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
public class CustomErrorController implements ErrorController {

	@GetMapping("/error")
	public String handleError() {
		return "error";
	}
	
	@ExceptionHandler(Exception.class)
    public String handleAnyException(Exception ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error"; 
    }
}
