package com.amin.technom.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

//@MultipartConfig
@Controller
public class UploadController {

	@Autowired
	private ServletContext context;
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(@RequestParam("imageFile") MultipartFile file) {

		// MultipartFile multipartFile = file.getFile();
		String uploadPath = context.getRealPath("") + "WEB-INF/images" + File.separator;
		// System.out.println(uploadPath + file.getOriginalFilename());
		try {
			FileCopyUtils.copy(file.getBytes(), new File(uploadPath + file.getOriginalFilename()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/addContentForm";
	}

}
