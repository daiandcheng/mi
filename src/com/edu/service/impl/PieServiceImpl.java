package com.edu.service.impl;

import java.util.List;

import com.edu.bean.Pie;
import com.edu.dao.PieDao;
import com.edu.dao.impl.PieDaoImpl;
import com.edu.service.PieService;

public class PieServiceImpl implements PieService {

	@Override
	public List<Pie> getPieList() {
		PieDao pieDao = new PieDaoImpl() ;
		return pieDao.getPieList();
	}

}
