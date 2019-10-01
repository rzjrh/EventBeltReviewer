package com.RezaAkh.web.EventBeltReviewer.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.RezaAkh.web.EventBeltReviewer.models.EventUser;
import com.RezaAkh.web.EventBeltReviewer.models.State;
import com.RezaAkh.web.EventBeltReviewer.services.UserService;
import com.RezaAkh.web.EventBeltReviewer.validators.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService uService;
	@Autowired
	private UserValidator validator;
	@GetMapping("/")
	public String Index(@ModelAttribute("registration") EventUser user, Model model) {
		model.addAttribute("states", State.States);
		return "/users/index.jsp";
	}
	@PostMapping("/")
	public String Register(@Valid @ModelAttribute("registration") EventUser user, BindingResult result, HttpSession session) {
		validator.validate(user, result);
		if(result.hasErrors())
			return "/users/index.jsp";
		EventUser newUser = this.uService.registerUser(user);
		session.setAttribute("userId", newUser.getId());
		return "redirect:/events";
	}
	@PostMapping("/login")
	public String Login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, RedirectAttributes redirs) {
		if(this.uService.authenticateUser(email, password)) {
			EventUser user = this.uService.getUserByEmail(email);
			session.setAttribute("userId", user.getId());
			return "redirect:/events";
		}
		redirs.addFlashAttribute("error", "Invalid Email/Password");
		return "redirect:/";
	}
}
