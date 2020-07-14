package com.amin.technom.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;


@Table(name = "content_tb")
@Entity
public class ContentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, columnDefinition = "VARCHAR(300)")
	//@NotEmpty(message = "عنوان نمی تواند خالی باشد")
	private String title;

	@Column(name = "html_content", nullable = false, columnDefinition = "TEXT")
	//@NotEmpty(message = "محتوا نمی تواند خالی باشد")
	private String htmlContent;

	@Column(name = "created_time", columnDefinition = "DATE")
	@DateTimeFormat(pattern="dd/MM/yyyy") 
	private Date createdTime;

	@Column(name = "edited_time", nullable = true, columnDefinition = "DATE")
	@DateTimeFormat(pattern="dd/MM/yyyy") 
	private Date editedTime;
	
	@Transient
	private String persianDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "writer")
	private UserModel writer;

	

	@OneToMany(mappedBy = "contentModel", orphanRemoval = true, cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<CommentModel> comments = new ArrayList<CommentModel>();

	public ContentModel() {
	}

	public ContentModel(long id, String title, String htmlContent, Date createdTime, Date editedTime, UserModel writer) {
		this.id = id;
		this.title = title;
		this.htmlContent = htmlContent;
		this.createdTime = createdTime;
		this.editedTime = editedTime;
		this.writer = writer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
	public Date getEditedTime() {
		return editedTime;
	}

	public void setEditedTime(Date editedTime) {
		this.editedTime = editedTime;
	}
	public String getPersianDate() {
		return persianDate;
	}
	public void setPersianDate(String persianDate) {
		this.persianDate = persianDate;
	}

	public UserModel getWriter() {
		return writer;
	}

	public void setWriter(UserModel writer) {
		this.writer = writer;
	}



	public List<CommentModel> getComments() {
		return comments;
	}

	public void setComments(List<CommentModel> comments) {
		this.comments = comments;
	}
	
	public void addComment(CommentModel commentModel) {
		comments.add(commentModel);
		commentModel.setContentModel(this);
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", title=" + title + ", htmlContent=" + htmlContent + ", createdTime="
				+ createdTime + ", editedTime=" + editedTime + ", creatorId=" + writer + 
				"]";
	}

}
