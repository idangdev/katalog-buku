package com.idangdev.catalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller	// ini ada @Component nya. jadi otomatis jadi bean oleh Spring Container. dan juga otomatis jadi Request Handler
public class HomeController {
	
	@GetMapping("/home") // anotasi lebih singkat daripada @RequestMapping.
//	@RequestMapping(value= "/home", method = RequestMethod.GET)	// ketika ada request dengan alamat /home dengan method GET maka akan di tangani oleh method home() ini
	public String home(Model model) {	// dia akan merender komponen view yang bernama home.html
		model.addAttribute("name", "Wildan");
		return "home";
	}
	
}
