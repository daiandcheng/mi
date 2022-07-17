package com.edu.service.impl;

import java.util.List;

import com.edu.bean.News;
import com.edu.dao.NewsDao;
import com.edu.dao.impl.NewsDaoImpl;
import com.edu.service.NewsService;

public class NewsServiceImpl implements NewsService {

	@Override
	public List<News> getLatestData() throws Exception {
		NewsDao newsDao = new NewsDaoImpl();
		return newsDao.getLatestData();
	}

}
