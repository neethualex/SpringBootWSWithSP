package com.trxade.webservice.repository;

import java.util.List;

import com.trxade.webservice.DO.TrxadeProduct;

/*
@Repository
@Transactional*/
public interface TrxadeRepository{ //extends CrudRepository<TrxadeProductEntity, Integer>{
	
	//@Procedure(name = "GETSELECT")
	//void getAllProducts(@Param("CMP") Integer arg0,@Param("CUST") String arg1,@Param("LOC")  String arg2,@Param("ORDERID") String arg3,@Param("SRCHCAT") String arg4, @Param("SRCHDATA") String arg5,@Param("PREMIER") String arg6);
	
	public List<TrxadeProduct> getAllProducts(Integer cmp,String cust, String loc,String orderId,String srchCat, String srchData, String premier);
	
	public List<TrxadeProduct> getAllProductsOnHand(Integer cmp,String cust, String loc,String orderId,String srchCat, String srchData, String premier);
}
