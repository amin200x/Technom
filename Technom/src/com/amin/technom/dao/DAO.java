package com.amin.technom.dao;

import java.util.List;


public interface DAO {
	List<Object> getAll();

	void add(Object model);

	void update(Object model);

	void delete(Object id);

	Object get(Object value);
}
