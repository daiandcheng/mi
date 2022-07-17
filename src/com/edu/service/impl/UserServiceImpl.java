package com.edu.service.impl;

import com.edu.bean.Msg;
import com.edu.bean.PageBean;
import com.edu.bean.User;
import com.edu.dao.UserDao;
import com.edu.dao.impl.UserDaoImpl;
import com.edu.service.UserService;
import com.edu.util.MailUtils;

public class UserServiceImpl implements UserService {

	@Override
	public void save(User user) throws Exception {
		UserDao userDao = new UserDaoImpl() ;
		userDao.save(user);
		// 保存成功之后，向邮箱发送一份激活码
		MailUtils.sendMail(user.getEmail(), 
				"欢迎你，请<a href='http://localhost:8080/mi/user?method=active&code="+user.getCode()+"'>激活</a>");
	}

	@Override
	public void active(String code) throws Exception {
		UserDao userDao = new UserDaoImpl() ;
		userDao.update(code);
	}

	@Override
	public Msg getUserByName(String name) throws Exception {
		UserDao userDao = new UserDaoImpl() ;
		User user = userDao.findUserByName(name);
		Msg msg = null ;
		if(null == user) {
			msg = new Msg(200, "当前用户可以使用");
		} else {
			msg = new Msg(500,"当前用户以存在");
		}
		return msg;
	}

	@Override
	public User getUser(String name) throws Exception {
		UserDao userDao = new UserDaoImpl() ;
		User user = userDao.findUserByName(name);
		return user;
	}

	@Override
	public PageBean getPageBean(int pageNo, int pagesize) throws Exception {
		UserDao userDao = new UserDaoImpl() ;
		return userDao.getPageBean(pageNo,pagesize);
	}

	@Override
	public User getUserById(String id) throws Exception {
		UserDao userDao = new UserDaoImpl() ;
		return userDao.getUserById(id);
	}

	@Override
	public void update(User user) throws Exception {
		UserDao userDao = new UserDaoImpl() ;
		userDao.update(user);
		
	}

	@Override
	public void deleteById(String id) throws Exception {
		UserDao userDao = new UserDaoImpl() ;
		userDao.deleteById(id);
		
	}

	@Override
	public void saveUser(User user) throws Exception {
		UserDao userDao = new UserDaoImpl() ;
		userDao.save(user);
		
	}

}
