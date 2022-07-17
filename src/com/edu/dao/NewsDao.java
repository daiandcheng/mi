package com.edu.dao;

import java.util.List;

import com.edu.bean.News;

public interface NewsDao {

	List<News> getLatestData() throws Exception ;

}
