package com.edu.dao;

import java.util.List;

import com.edu.bean.Advice;

public interface AdviceDao {

	List<Advice> getLatestData() throws Exception ;

}
