package com.edu.util;
/**
 *这是数据库连接的工具类
 * @author daixianren
 *
 */

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.edu.contant.Constant;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtil {
	// 这是创建了一个数据源
	private static DataSource dataSource = new ComboPooledDataSource() ;
	// 确保conn 这个连接在各自的线程中，不会出现线程并发的问题。
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>() ;
	
	
//	public static void main(String[] args) throws Exception{
//		System.out.println(getDataSource());
//		System.out.println(getConnection());
//	}
	/**
	 * 获取数据源
	 * @return
	 */
	public static DataSource getDataSource() {
		return dataSource ;
		
	}
	/**
	 * 这是获取数据库连接
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = threadLocal.get() ;
		if(null == conn) {
			conn = dataSource.getConnection() ;
			threadLocal.set(conn);
		}
		return conn ;
	}
	/**
	 * 手动的开启事务
	 * @throws SQLException 
	 */
	public static void startTransaction() throws SQLException{
		getConnection().setAutoCommit(false);
	}
	// 事务回滚
	public static void rollback() throws SQLException {
		getConnection().rollback();
	}

	// 事务提交
	public static void commitAndReleased() throws SQLException {

		getConnection().commit(); // 事务提交
		getConnection().close();// 释放connection，是将其放回到连接池.
		threadLocal.remove();
	}

}
