package com.amrut.prabhu.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("orders")
public class RootController {
	@GetMapping
	@RolesAllowed({"omg_operator"})
	public String index(Model model) {
		model.addAttribute("pageTitle", "Taming Thymeleaf B");
		model.addAttribute("scientists",
				List.of("Albert Einstein",
				"Niels Bohr",
				"James Clerk Maxwell"));
		return "index";
	}
}