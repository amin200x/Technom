package com.amin.technom.service;

import java.util.List;



public interface Service {

	List<Object> getAll();
	void add(Object model);
	void update(Object model);
	void delete(Object id);
	Object get(Object value);


}
