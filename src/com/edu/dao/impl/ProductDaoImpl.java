package com.edu.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.edu.bean.Product;
import com.edu.contant.Constant;
import com.edu.dao.ProductDao;
import com.edu.util.DataSourceUtil;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> getPageList(int pageNo, int pagesize) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		String sql = "select * from tbl_product limit ?,?";
		return query.query(sql, new BeanListHandler<Product>(Product.class), pagesize*(pageNo-1),pagesize);
	}

	@Override
	public int getRowCount() throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		String sql = "select count(*) from tbl_product";
		return ((Long)query.query(sql, new ScalarHandler())).intValue();
	}

	@Override
	public void save(Product p) throws Exception {
		// TODO Auto-generated method stub
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		String sql ="INSERT INTO tbl_product VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
		query.update(sql, p.getId(),p.getTitle(),p.getPic(),p.getStock(),p.getPrice(),p.getCode(),p.getCategory_id(),
				p.getBrand_id(),p.getPro_price(),p.getHot_recommand(),p.getProduct_off(),p.getProduct_up_date(),p.getProduct_off_date());
	}

	@Override
	public List<Product> getProduct(int num) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		String sql = "" ;
		if(num == 1) {
			sql ="select * from tbl_product where hot_recommand="+Constant.HOT_RECOMMAND+" and product_off="+Constant.UN_PRODUCT_OFF+" order by product_up_date desc" ;
		} else if(num ==2) {
			sql ="select * from tbl_product where pro_price="+Constant.PRO_PRICE+" and product_off="+Constant.UN_PRODUCT_OFF+" order by product_up_date desc" ;
		}
		return query.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public Product getProductById(String id) throws Exception {
		QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource()) ;
		return query.query("select * from tbl_product where id=?", new BeanHandler<Product>(Product.class), id);
	}

}
