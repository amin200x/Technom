package com.amin.technom.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.amin.technom.constant.Constants;
import com.sun.istack.NotNull;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Entity
@Table(name = "comment_tb")
public class CommentModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//@Column(name = "userName", columnDefinition = "VARCHAR(80)", nullable = false)
	//private String userName;
	
	//@Email(regexp = Constants.EMAIL_REGULAR_EXPRESSION, message = "قالب ایمیل صحیح نیست(ali@yahoo.com)")
	//@NotNull
	//@Column(name = "email", length = 100, nullable = true)
	//private String email;

	@Column(columnDefinition = "TEXT", nullable = false)
	//@NotBlank(message = "نظر نمی تواند خالی باشد")
	private String comment;

	@Column(name = "created_time", columnDefinition = "DATE")
	@DateTimeFormat(pattern="dd/MM/yyyy") 
	private Date createdTime;

	@Transient
	private String persianDate;
	
	private boolean visibility;

	
	@ManyToOne
	@JoinColumn(name = "content_id")
	private ContentModel contentModel;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username", insertable = true, updatable = false)
	private UserModel user;
	
	@OneToMany(mappedBy = "commentModel", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ReplyCommentModel> replies = new ArrayList<ReplyCommentModel>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getPersianDate() {
		return persianDate;
	}
	public void setPersianDate(String persianDate) {
		this.persianDate = persianDate;
	}
	public ContentModel getContentModel() {
		return contentModel;
	}

	public UserModel getUser() {
		return user;
	}
	
	public void setUser(UserModel user) {
		this.user = user;
	}
	public void setContentModel(ContentModel contentModel) {
		this.contentModel = contentModel;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
	
	public List<ReplyCommentModel> getReplies() {
		return replies;
	}
	public void setReplies(List<ReplyCommentModel> replies) {
		this.replies = replies;
	}
	public void addReplyToComment(ReplyCommentModel replyCommentModel) {
		replies.add(replyCommentModel);
		replyCommentModel.setCommentModel(this);
	}


	

}
