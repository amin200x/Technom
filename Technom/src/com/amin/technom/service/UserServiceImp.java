package com.amin.technom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.amin.technom.dao.DAO;


@org.springframework.stereotype.Service("userServiceImp")
public class UserServiceImp implements Service {

	@Autowired
	@Qualifier("userDAO")
	private DAO dao;

	@Transactional
	@Override
	public List<Object> getAll() {

		return dao.getAll();
	}

	@Transactional
	@Override
	public void add(Object model) {
		dao.add(model);

	}

	@Transactional
	@Override
	public void update(Object model) {
		dao.update(model);

	}

	@Transactional
	@Override
	public void delete(Object value) {
		dao.delete(value);

	}

	@Override
	@Transactional
	public Object get(Object value) {

		return dao.get(value);
	}

}
