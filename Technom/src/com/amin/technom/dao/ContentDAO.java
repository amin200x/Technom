package com.amin.technom.dao;

import java.util.Collections;
import java.util.Date;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amin.technom.model.ContentModel;

@Repository("contentDAO")
public class ContentDAO implements DAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Object> getAll() {
		Session session = sessionFactory.getCurrentSession();

		List<Object> contents = session.createQuery("From ContentModel").getResultList();
		Collections.reverse(contents);
		return contents;
	}
	@Override
	public void add(Object model) {
		Session session = sessionFactory.getCurrentSession();
		ContentModel contentModel = (ContentModel)model;
		contentModel.setCreatedTime(new Date());
		
		session.save(contentModel);

	}
	@Override
	public void update(Object model) {
		Session session = sessionFactory.getCurrentSession();
		ContentModel content = (ContentModel)model;
	/*	ContentModel contentModel = session.get(ContentModel.class, content.getId());
		
		contentModel.setEditedTime(new Date());
		contentModel.setHtmlContent(content.getHtmlContent());
		contentModel.setTitle(content.getTitle());
		contentModel.setWriter(content.getWriter());
		*/
		session.update(content);

	}

	@Override
	public void delete(Object value) {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("Delete From ContentModel Where id=:id").setParameter("id", (long)value).executeUpdate();

	}
	@Override
	public Object get(Object value) {
		Session session = sessionFactory.getCurrentSession();
		if(value instanceof String) {
			System.out.println("InstanceOf String: " + value);
			List<Object> contents = session.createQuery("from ContentModel cm  Where cm.title Like :searchValue")
					.setParameter("searchValue", "%"+value+"%")
					.getResultList();

			return contents;
		}
		
		Object model = session.createQuery("From ContentModel cm Left Join Fetch cm.writer Where cm.id=:id")
		.setParameter("id", (long)value).uniqueResult();
		return model;
	}


}
