package com.amin.technom.dao;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amin.technom.model.CommentModel;
import com.amin.technom.model.ContentModel;

@Repository("commentDAO")
public class CommentDAO implements DAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Object> getAll() {
		
		return new ArrayList<Object>();
	}

	@Override
	public void add(Object model) {
		Session session = sessionFactory.getCurrentSession();
		session.save((CommentModel)model);

	}
	@Override
	public void update(Object model) {
		Session session = sessionFactory.getCurrentSession();
		if(model instanceof ContentModel) {
			ContentModel merged = (ContentModel) session.merge((CommentModel)model);
			session.persist(merged);
		}else {
			session.update((CommentModel)model);
		}
		

	}
	
	@Override
	public Object get(Object model) {
		Session session = sessionFactory.getCurrentSession();

		List<CommentModel> list = session
				.createQuery("From CommentModel cm Where cm.contentModel=:contentModel", CommentModel.class)
				.setParameter("contentModel", (ContentModel)model).getResultList();
		((ContentModel)model).setComments(list);
		return model;

	}

	@Override
	public void delete(Object value) {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("Delete From CommentModel Where id=:id").setParameter("id", (long)value).executeUpdate();

	}



	
}
