package com.amin.technom.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amin.technom.model.CommentModel;
import com.amin.technom.model.ReplyCommentModel;
import com.amin.technom.service.Service;

//@MultipartConfig
@Controller
public class ReplyCommentController {

	@Autowired
	@Qualifier("replyCommentServiceImp")
	private Service service;

	@PostMapping(value = "/replyToComment/{commentId}/{contentId}")
	public String replyToComment(@ModelAttribute("replyCommentModel") ReplyCommentModel replyCommentModel,
			@PathVariable("contentId") long contentId, @PathVariable("commentId") long commentId) {
		CommentModel commentModel = new CommentModel();
		commentModel.setId(commentId);
		replyCommentModel.setCommentModel(commentModel);
		replyCommentModel.setRepliedTime(new Date());
		service.add(replyCommentModel);
		// System.out.println();

		return "redirect:/showContent/" + contentId;
	}

	@RequestMapping(value = "/deleteReply/{replyId}/{contentId}", method = RequestMethod.GET)
	public String deleteReply(@PathVariable("replyId") long replyId, @PathVariable("contentId") long contentId) {

		service.delete(replyId);
		return "redirect:/showContent/" + contentId;
	}

}
