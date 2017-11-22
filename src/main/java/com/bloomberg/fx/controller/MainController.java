package com.bloomberg.fx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class MainController {
	
	@RequestMapping("/file/upload")
	private String uploadFile() {
		
		return "upload";
	}

	@RequestMapping("/file/search")
	private String getFileDetailes() {
		return "search";
	}
	
	@RequestMapping("/")
    public RedirectView redirectWithUsingRedirectView() {
        return new RedirectView("/file/upload");
    }
}
