package com.amin.technom.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.amin.technom.constant.AuthorityType;
import com.amin.technom.model.UserModel;
import com.amin.technom.service.Service;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userServiceImp")
	private Service service;

	@RequestMapping(value = "/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

	@GetMapping(value = "/addUserForm")
	public String addUserForm(Model model) {
		UserModel newUser = new UserModel();
		// AuthorityModel authority = new AuthorityModel();
		// newUser.setAuthority(authority);
		model.addAttribute("user", newUser);
		// model.addAttribute("authorities", AuthorityType.values());

		return "addUserForm";
	}

	@PostMapping(value = "/addUser")
	public String addUser(Model model, @ModelAttribute("user") @Valid UserModel user, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("Has Error");
			return "addUserForm";
		} else {
			System.out.println("Has no Error");
			service.add(user);
			return "redirect:/login";
		}

	}

	@PostMapping(value = "/updateUser")
	public String updateUser(Model model, @ModelAttribute("user") @Valid UserModel user, BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "updateUserForm";
		} else {
			service.update(user);
		}

		return "redirect:/";
	}

	@GetMapping(value = "/userList")
	public String userList(Model model) {
		List<UserModel> users = (List<UserModel>) (List) service.getAll();
		model.addAttribute("users", users);

		return "userList";
	}

	@GetMapping(value = "/updateUserForm/{userName}")
	public String updateUserForm(Model model, @PathVariable("userName") String userName) {
		UserModel user = (UserModel) service.get(userName);
		model.addAttribute("user", user);
		model.addAttribute("authorities", AuthorityType.values());

		return "updateUserForm";
	}

	@GetMapping(value = "/resetPasswordForm")
	public String resetPasswordForm(Model model) {

		model.addAttribute("user", new UserModel());

		return "resetPasswordForm";
	}

	@PostMapping(value = "/resetPassword")
	public String resetPassword(Model model, @ModelAttribute("user") @Valid UserModel user, BindingResult result) {
		
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "resetPasswordForm";
		} else {
			UserModel userModel = (UserModel) service.get(user.getUserName());
			if (userModel != null && userModel.getEmail().equalsIgnoreCase(user.getEmail())) {
					service.update(user);
					
				} else {
					model.addAttribute("user", user);
					model.addAttribute("errorMessage", "نام کاربری یا ایمیل اشتباه است");
					return "resetPasswordForm";
				}
			}
		
		
		return "redirect:/login";
	}

	@GetMapping(value = "/deleteUser/{userName}")
	public String deleteUser(Model model, @PathVariable("userName") String userName) {
		service.delete(userName);

		return "redirect:/";
	}

	@PostMapping(value = "/logout")
	public String logout() {

		return "redirect:/";
	}

}
