package com.amin.technom.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amin.technom.model.CommentModel;
import com.amin.technom.model.ContentModel;
import com.amin.technom.model.ReplyCommentModel;
import com.amin.technom.model.UserModel;
import com.amin.technom.service.Service;
import com.github.mfathi91.time.PersianDate;

//@MultipartConfig
@Controller
public class ContentController {

	@Autowired
	@Qualifier("contentServiceImp")
	private Service service;
	private List<ContentModel> contents;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ModelAndView homepge() {
		contents = (List<ContentModel>) (List) service.getAll();
		for (ContentModel item : contents) {
			String persianDate = "";
			if (item.getEditedTime() != null)
				persianDate = getPersianDate(item.getEditedTime());
			else
				persianDate = getPersianDate(item.getCreatedTime());

			item.setPersianDate(persianDate);

		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homepage");
		modelAndView.addObject("contents", contents);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/latestContents")
	public ModelAndView latestContents() {
		contents = (List<ContentModel>) (List) service.getAll();
		if (contents.size() > 10)
			contents = contents.subList(0, 10);
		for (ContentModel item : contents) {
			String persianDate = "";
			if (item.getEditedTime() != null)
				persianDate = getPersianDate(item.getEditedTime());
			else
				persianDate = getPersianDate(item.getCreatedTime());

			item.setPersianDate(persianDate);

		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("homepage");
		modelAndView.addObject("contents", contents);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/showContent/{contentId}")
	public String showContent(@PathVariable("contentId") long contentId, Model model) {
		// List<ContentModel> contents = service.getAllHtmlContent();
		ContentModel contentModel = (ContentModel) service.get(contentId);
		String persianDate = "";
		if (contentModel.getEditedTime() != null)
			persianDate = getPersianDate(contentModel.getEditedTime());
		else
			persianDate = getPersianDate(contentModel.getCreatedTime());

		contentModel.setPersianDate(persianDate);

		for (CommentModel comment : contentModel.getComments()) {
			comment.setPersianDate(getPersianDate(comment.getCreatedTime()));
			for (ReplyCommentModel reply : comment.getReplies()) {
				reply.setPersianDate(getPersianDate(reply.getRepliedTime()));

			}
		}

		CommentModel comment = new CommentModel();
		comment.setUser(new UserModel());

		ReplyCommentModel reply = new ReplyCommentModel();
		reply.setUser(new UserModel());

		model.addAttribute("contentModel", contentModel);
		model.addAttribute("commentModel", comment);
		model.addAttribute("replyCommentModel", reply);
		if (contentModel.getHtmlContent() == null)
			return "redirect:/";
		return "showContent";
	}

	@RequestMapping("/addContentForm")
	public String addContentForm(Model model) {
		ContentModel content = new ContentModel();
		model.addAttribute("contentModel", content);
		return "addContentForm";
	}

	@RequestMapping(value = "/addContent", method = RequestMethod.POST)
	public String addContent(Model model, @ModelAttribute("contentModel") @Valid ContentModel content,
			BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("contentModel", content);
		} else {
			service.add(content);

		}
		// System.out.println(content);

		return "redirect:/addContentForm";
	}

	@RequestMapping("/updateContentForm/{contentId}")
	public String updateContentForm(@PathVariable("contentId") long contentId, Model model) {

		ContentModel contentModel;

		contentModel = (ContentModel) service.get(contentId);

		model.addAttribute("contentModel", contentModel);

		return "updateContentForm";
	}

	@RequestMapping(value = "/updateContent", method = RequestMethod.POST)
	public String updateContent(@ModelAttribute("contentModel") ContentModel content) {
		content.setEditedTime(new Date());
		service.update(content);

		return "redirect:/";
	}

	@RequestMapping("/deleteContent/{contentId}")
	public String deleteContent(@PathVariable("contentId") long contentId) {
		service.delete(contentId);
		return "redirect:/";
	}

	@RequestMapping(value = "/contentList", method = RequestMethod.GET)
	public String contentList(Model model) {

		contents = (List<ContentModel>) (List) service.getAll();
		model.addAttribute("contents", contents);

		return "contentList";
	}

	@GetMapping(value = "/searchContent")
	public String searchContent(Model model, @RequestParam("searchParam") String strValue) {
		System.out.println(strValue);
		if (strValue.isEmpty()) {

			return "redirect:/";
		}
		contents = (List<ContentModel>) (List) service.get(strValue);
		model.addAttribute("contents", contents);

		return "homepage";
	}

	@RequestMapping(value = "/403")
	public String error40s() {
		return "403";
	}

	private String getPersianDate(Date date) {
		String persianDate = "";
		if (date != null) {
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (localDate != null) {
				persianDate = PersianDate.fromGregorian(localDate).toString();
			}
		}
		return persianDate;

	}
	// This methos has gone to ExceptionHandler class!
	/*
	 * @ExceptionHandler(Exception.class) public String exceptionHandler(Exception
	 * ex) { return "error"; }
	 */
}
