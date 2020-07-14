package com.amin.technom.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amin.technom.model.CommentModel;
import com.amin.technom.model.ContentModel;
import com.amin.technom.model.ReplyCommentModel;
import com.amin.technom.service.Service;

//@MultipartConfig
@Controller
public class CommentController {


	@Autowired(required = true)
	@Qualifier(value  = "commentServiceImp")
	private Service commentService;
	
	@Autowired
	@Qualifier("contentServiceImp")
	private Service contentService;
	private List<ContentModel> contents;
	
	@RequestMapping(value = "/addComment/{contentId}", method = RequestMethod.POST)
	public String addComment( @PathVariable("contentId") long contentId,
			@ModelAttribute("commentModel") CommentModel commentModel) {

			ContentModel contentModel = new ContentModel();
			contentModel.setId(contentId);
			commentModel.setCreatedTime(new Date());
			commentModel.setContentModel(contentModel);
			
			System.out.println(commentModel.getUser().getUserName());
			commentService.add(commentModel);

		return "redirect:/showContent/" + contentId;

	}

	@RequestMapping(value = "/comments/{contentId}", method = RequestMethod.GET)
	public String comments(@PathVariable("contentId") long contentId, Model model) {

		ContentModel contentModel =(ContentModel) contentService.get(contentId);
		//Loading Comments
		commentService.get(contentModel);
		model.addAttribute("contentModel", contentModel);
		return "showComments";

	}

	@RequestMapping(value = "/updateComment", method = RequestMethod.POST)
	public String updateComment(@ModelAttribute("contentModel") ContentModel contentModel) {
		/*ContentModel tempContent = (ContentModel) contentService.get(contentModel.getId());
		for (CommentModel item : contentModel.getComments()) {
			for (CommentModel temp : tempContent.getComments()) {
				if (temp.getId() == item.getId()) {
					temp.setVisibility(item.isVisibility());
					for (ReplyCommentModel reply : item.getReplies()) {
						for (ReplyCommentModel tempReply : temp.getReplies()) {
							if (tempReply.getId() == reply.getId()) {
								tempReply.setVisibility(reply.isVisibility());
							}
						}
					}
				}

			}

		}*/
		//System.out.println(contentModel);
		//System.out.println("Comments: " + contentModel.getComments());
		//System.out.println("Replies: " + contentModel.getComments().get(0).getReplies());
		contentService.update(contentModel);
		return "redirect:/showContent/" + contentModel.getId();
	}

	@RequestMapping(value = "/deleteComment/{commentId}/{contentId}", method = RequestMethod.GET)
	public String deleteComment(@PathVariable("commentId") long commentId, @PathVariable("contentId") long contentId) {

		commentService.delete(commentId);
		return "redirect:/showContent/" + contentId;	
		}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	binder.registerCustomEditor(Date.class, new CustomDateEditor(
	        dateFormat, false));
	
	}
}
