package com.edu.service.impl;

import java.util.List;

import com.edu.bean.Brand;
import com.edu.dao.BrandDao;
import com.edu.dao.impl.BrandDaoImpl;
import com.edu.service.BrandService;

public class BrandServiceImpl implements BrandService {

	@Override
	public List<Brand> getAll() throws Exception {
		BrandDao brandDao = new BrandDaoImpl();
		return brandDao.getAll();
	}

}
