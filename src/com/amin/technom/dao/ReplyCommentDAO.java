package com.amin.technom.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amin.technom.model.ReplyCommentModel;



@Repository("replyCommentDAO")
public class ReplyCommentDAO implements DAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void add(Object model) {
		Session session = sessionFactory.getCurrentSession();
		session.save((ReplyCommentModel) model);

	}

	@Override
	public void update(Object model) {
		Session session = sessionFactory.getCurrentSession();
		session.update((ReplyCommentModel) model);

	}

	@Override
	public void delete(Object value) {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("Delete From ReplyCommentModel Where id=:id")
		.setParameter("id", (long) value)
				.executeUpdate();
	}

	@Override
	public List<Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
