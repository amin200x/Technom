package com.amin.technom.model;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.amin.technom.constant.AuthorityType;

@Entity
@Table(name = "authorities", uniqueConstraints = @UniqueConstraint(columnNames = { "username", "authority" }))
public class AuthorityModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
*/
	@Id
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
	@JoinColumn(name = "username", nullable = false, referencedColumnName = "username", unique = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserModel user;

	@Enumerated(EnumType.STRING)
	@Column(name = "authority", length = 35)
	private AuthorityType authorityType;

	public AuthorityType getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(AuthorityType authorityType) {
		this.authorityType = authorityType;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Authority [user=" + user + ", authorityType=" + authorityType + "]";
	}

}
