package com.edu.service;

import com.edu.bean.Msg;
import com.edu.bean.PageBean;
import com.edu.bean.User;

public interface UserService {

	void save(User user) throws Exception ;

	void active(String code) throws Exception ;

	Msg getUserByName(String name)  throws Exception ;

	User getUser(String name) throws Exception ;

	PageBean getPageBean(int pageNo, int pagesize) throws Exception ;

	User getUserById(String id)  throws Exception ;

	void update(User user)  throws Exception ;

	void deleteById(String id) throws Exception ;

	void saveUser(User user) throws Exception ;

}
