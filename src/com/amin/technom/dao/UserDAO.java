package com.amin.technom.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.amin.technom.constant.AuthorityType;
import com.amin.technom.model.AuthorityModel;
import com.amin.technom.model.UserModel;

@Repository("userDAO")
public class UserDAO implements DAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void add(Object model) {
		UserModel user = (UserModel) model;
		Session session = sessionFactory.getCurrentSession();
		try {
			BCryptPasswordEncoder passwordEncriptor = new BCryptPasswordEncoder();
			user.setPassword(passwordEncriptor.encode(user.getPassword()));
			user.setConfirmPassword(user.getPassword());
			user.setEnabled(true);
			user.setAuthority(new AuthorityModel());
			user.getAuthority().setAuthorityType(AuthorityType.ROLE_USER);
			user.getAuthority().setUser(user);
			session.persist(user);

		} catch (Exception e) {

		} finally {

		}

	}

	@Override
	public void update(Object model) {
		UserModel user = (UserModel) model;

		Session session = sessionFactory.getCurrentSession();

		if (user.getFullName().equals("resetPassword001")) {
			UserModel userModel = session.get(UserModel.class, user.getUserName());
			if (userModel != null) {
				BCryptPasswordEncoder passwordEncriptor = new BCryptPasswordEncoder();
				userModel.setPassword(passwordEncriptor.encode(user.getPassword()));
				userModel.setConfirmPassword(userModel.getPassword());
			}

		} else {
			user.setConfirmPassword(user.getPassword());
			user.getAuthority().setUser(user);
			session.update(user);
			session.update(user.getAuthority());
		}

	}

	@Override
	public List<Object> getAll() {
		Session session = sessionFactory.getCurrentSession();

		List<Object> users = session.createQuery("From UserModel u Inner Join fetch u.authority").getResultList();

		return users;
	}

	@Override
	public void delete(Object value) {
		Session session = sessionFactory.getCurrentSession();
		UserModel user = (UserModel) session
				.createQuery("From UserModel u Inner Join fetch u.authority Where u.userName=:userName")
				.setParameter("userName", (String) value).uniqueResult();
		if (user != null) {
			if (user.getAuthority().getAuthorityType() == AuthorityType.ROLE_ADMIN) {
				List<Object> users = session
						.createQuery(
								"From UserModel u Inner Join fetch u.authority a Where a.authorityType =:authorityType")
						.setParameter("authorityType", AuthorityType.ROLE_ADMIN).getResultList();
				if (users.size() >= 2)
					session.delete(user);
			} else {

				session.createQuery("Delete From UserModel Where userName=:userName")
						.setParameter("userName", (String) value).executeUpdate();
			}
		}
	}

	@Override
	public UserModel get(Object value) {
		Session session = sessionFactory.getCurrentSession();
		UserModel userModel = session.createQuery("From UserModel Where userName=:userName", UserModel.class)
				.setParameter("userName", (String) value).uniqueResult();
		return userModel;
	}

}
