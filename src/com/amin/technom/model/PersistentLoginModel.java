package com.amin.technom.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "persistent_logins")
public class PersistentLoginModel {

	@Id
	@Column(name = "series", length = 64, nullable = false)
	private String series;

	@Column(name = "token", length = 64, nullable = false)
	private String token;

	@Column(name = "last_used", length = 64, nullable = false, columnDefinition = "TIMESTAMP")
	@UpdateTimestamp
	private Date lastUsed;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
	@JoinColumn(name = "username", referencedColumnName = "username")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserModel user;

	public PersistentLoginModel() {
	}

	public PersistentLoginModel(String seriers, String token, Date lastUsed, UserModel user) {
		this.series = seriers;
		this.token = token;
		this.lastUsed = lastUsed;
		this.user = user;
	}

	public String getSeriers() {
		return series;
	}

	public void setSeriers(String seriers) {
		this.series = seriers;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

}
