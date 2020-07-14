package com.amin.technom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.amin.technom.constant.Constants;

@Entity
@Table(name = "reply_comment_tb")
public class ReplyCommentModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// Column(name = "userName", columnDefinition = "VARCHAR(80)", nullable = false)
	// private String userName;

	// @Email(regexp = Constants.EMAIL_REGULAR_EXPRESSION, message = "قالب ایمیل
	// صحیح نیست(ali@yahoo.com)")
	// @Column(name = "email", length = 100, nullable = true)
	// private String email;

	@Column(name = "reply_comment", columnDefinition = "TEXT", nullable = false)
	private String replyComment;

	private boolean visibility;

	@Column(name = "replied_time", columnDefinition = "DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date repliedTime;

	@Transient
	private String persianDate;

	@ManyToOne
	@JoinColumn(name = "comment_id")
	private CommentModel commentModel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username", updatable = false)
	private UserModel user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReplyComment() {
		return replyComment;
	}

	public void setReplyComment(String replyComment) {
		this.replyComment = replyComment;
	}

	public Date getRepliedTime() {
		return repliedTime;
	}

	public void setRepliedTime(Date repliedTime) {
		this.repliedTime = repliedTime;
	}

	public String getPersianDate() {
		return persianDate;
	}

	public void setPersianDate(String persianDate) {
		this.persianDate = persianDate;
	}

	public CommentModel getCommentModel() {
		return commentModel;
	}

	public void setCommentModel(CommentModel commentModel) {
		this.commentModel = commentModel;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

}
