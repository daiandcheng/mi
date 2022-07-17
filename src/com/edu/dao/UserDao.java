package com.edu.dao;

import com.edu.bean.PageBean;
import com.edu.bean.User;

public interface UserDao {

	void save(User user) throws Exception ;

	void update(String code) throws Exception ;

	User findUserByName(String name) throws Exception ;

	PageBean getPageBean(int pageNo, int pagesize)  throws Exception ;

	User getUserById(String id) throws Exception ;

	void update(User user) throws Exception ;

	void deleteById(String id) throws Exception ;


}
