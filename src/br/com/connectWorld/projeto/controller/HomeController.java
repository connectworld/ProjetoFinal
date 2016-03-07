package br.com.connectWorld.projeto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/chamaHome")
	public String chamaHome() {
		return "principal/home";
	}
	
}
