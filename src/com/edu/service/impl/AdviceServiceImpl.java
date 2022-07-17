package com.edu.service.impl;

import java.util.List;

import com.edu.bean.Advice;
import com.edu.dao.AdviceDao;
import com.edu.dao.impl.AdviceDaoImpl;
import com.edu.service.AdviceService;

public class AdviceServiceImpl implements AdviceService {
	
	private AdviceDao adviceDao ;
	@Override
	public List<Advice> getLatestData() throws Exception {
//		AdviceDao adviceDao = new AdviceDaoImpl();
		return adviceDao.getLatestData();
	}

}
