package com.amin.technom.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.amin.technom.config.FieldMatch;
import com.amin.technom.constant.Constants;

@FieldMatch.List({
		@FieldMatch(first = "password", second = "confirmPassword", message = "رمز ورود با رمز ورود تایید برابر نیست") })
@Entity
@Table(name = "users")
public class UserModel {

	@Size(min = 3, max = 30, message = "طول نام کاربری حداقل 3 و حداکثر 30 کاراکتر باشد")
	@Id
	@Column(name = "username", length = 30, nullable = false, unique = true)
	@Pattern(regexp = Constants.ENGLISH_WORDS_REGULAR_EXPRESSION, message = "نام کاربری باید با حروف انگلیسی شروع شود")
	private String userName;

	@Column(name = "full_name", length = 30, nullable = false)
	@NotBlank(message = "نام نمی تواند خالی باشد")
	private String fullName;

	@NotBlank(message = "ایمیل نمی تواند خالی باشد")
	@Email(regexp = Constants.EMAIL_REGULAR_EXPRESSION, message = "قالب ایمیل صحیح نیست(ali@yahoo.com)")
	@Column(name = "email", length = 100)
	private String email;

	@NotBlank(message = "رمز ورود نمی تواند خالی باشد")
	@Column(name = "password", length = 80)
	@Pattern(regexp = Constants.PASSWORD_REGULAR_EXPRESSION, message = "رمز ورود باید شامل حروف بزرگ و کوچک انگلیسی و اعداد باشد")
	private String password;

	@Transient()
	private String confirmPassword;

	private boolean enabled;

	@Column(name = "created_time", columnDefinition = "DATE")
	@UpdateTimestamp
	private Date createdTime;

	@OneToOne(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true, fetch = FetchType.LAZY)
	private AuthorityModel authority;

	@OneToOne(mappedBy = "user")
	private PersistentLoginModel persistentLogin;
	
	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ReplyCommentModel> replies = new ArrayList<ReplyCommentModel>();
	
	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<CommentModel> comments = new ArrayList<CommentModel>();

	@OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
	private List<ContentModel> contents = new ArrayList<ContentModel>();

	public UserModel() {
	}

	public UserModel(String userName, String email, String password, boolean enabled) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public AuthorityModel getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityModel authority) {
		this.authority = authority;
	}

	public PersistentLoginModel getPersistentLogin() {
		return persistentLogin;
	}

	public void setPersistentLogin(PersistentLoginModel persistentLogin) {
		this.persistentLogin = persistentLogin;
	}

	public List<ReplyCommentModel> getReplies() {
		return replies;
	}
	
	public void setReplies(List<ReplyCommentModel> replies) {
		this.replies = replies;
	}
	
	public List<CommentModel> getComments() {
		return comments;
	}
	public void setComments(List<CommentModel> comments) {
		this.comments = comments;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", email=" + email + ", password=" + password + ", enabled=" + enabled
				+ ", authority=" + authority + ", persistentLogin=" + persistentLogin + "]";
	}

}
