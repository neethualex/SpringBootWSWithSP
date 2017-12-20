package com.trxade.webservice.repository.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.trxade.webservice.DO.TrxadeProduct;
import com.trxade.webservice.repository.TrxadeRepository;

@Repository
public class TrxadeRepositoryImpl implements TrxadeRepository {

	@PersistenceContext
	private EntityManager entiryManager;
	
	@Value("${sp.getalltrxadeproducts}")
	private String getAllProductsSP;
	
	@Override
	public List<TrxadeProduct> getAllProducts(Integer cmp,String cust, String loc,String orderId,String srchCat, String srchData, String premier) {
		StoredProcedureQuery storedProcedure = entiryManager.createStoredProcedureQuery(getAllProductsSP);
		String argu0 = "CMP";
		String argu1 = "CUST";
		String argu2 = "LOC";
		String argu3 = "ORDERID";
		String argu4 = "SRCHCAT";
		String argu5 = "SRCHDATA";
		String argu6 = "PREMIER";
		
		//DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		//Date date = format.parse(string);
		
		storedProcedure.registerStoredProcedureParameter(argu0, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(argu1, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(argu2, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(argu3, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(argu4, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(argu5, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(argu6, String.class, ParameterMode.IN);
		
		storedProcedure.setParameter(argu0, cmp);
		storedProcedure.setParameter(argu1, cust);
		storedProcedure.setParameter(argu2, loc);
		storedProcedure.setParameter(argu3, orderId);
		storedProcedure.setParameter(argu4, srchCat);
		storedProcedure.setParameter(argu5, srchData);
		storedProcedure.setParameter(argu6, premier);
		
		List<Object[]> storedProcedureResult = storedProcedure.getResultList();
		
		/*return storedProcedureResult.stream().map(result -> new TrxadeProduct(
				"NDC", result[65].toString(), result[10].toString(), result[23].toString(), result[25].toString(),
				result[12].toString(), result[68].toString(), result[16].toString(), result[3].toString(), (BigDecimal)result[9],
				result[10].toString(), result[11].toString(), "", "", "0",
				(BigDecimal)result[35], result[21].toString()
		   )).collect(Collectors.toList());*/
		
		return storedProcedureResult.stream().map(result -> new TrxadeProduct(
				"NDC", result[65].toString(), result[10].toString(), result[23].toString(), result[25].toString(),
				result[12].toString(), result[68].toString(), result[16].toString(), result[3].toString(), (BigDecimal)result[9],
				"", "", "", "", "0",
				(BigDecimal)result[35], result[21].toString()
		   )).collect(Collectors.toList());
	}

	@Override
	public List<TrxadeProduct> getAllProductsOnHand(Integer cmp, String cust, String loc, String orderId,
			String srchCat, String srchData, String premier) {
		StoredProcedureQuery storedProcedure = entiryManager.createStoredProcedureQuery(getAllProductsSP);
		String argu0 = "CMP";
		String argu1 = "CUST";
		String argu2 = "LOC";
		String argu3 = "ORDERID";
		String argu4 = "SRCHCAT";
		String argu5 = "SRCHDATA";
		String argu6 = "PREMIER";
		
		//DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		//Date date = format.parse(string);
		
		storedProcedure.registerStoredProcedureParameter(argu0, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(argu1, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(argu2, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(argu3, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(argu4, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(argu5, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(argu6, String.class, ParameterMode.IN);
		
		storedProcedure.setParameter(argu0, cmp);
		storedProcedure.setParameter(argu1, cust);
		storedProcedure.setParameter(argu2, loc);
		storedProcedure.setParameter(argu3, orderId);
		storedProcedure.setParameter(argu4, srchCat);
		storedProcedure.setParameter(argu5, srchData);
		storedProcedure.setParameter(argu6, premier);
		
		List<Object[]> storedProcedureResult = storedProcedure.getResultList();
		
		return storedProcedureResult.stream().map(result -> new TrxadeProduct(
				"NDC", result[65].toString(), result[10].toString(), result[23].toString(), result[25].toString(),
				result[12].toString(), result[68].toString(), result[16].toString(), result[3].toString(), (BigDecimal)result[9],
				"", "", "", "0", "0",
				(BigDecimal)result[35], result[21].toString()
		   )).collect(Collectors.toList());
		
		/*return storedProcedureResult.stream().map(result -> new TrxadeProduct(
				"NDC", result[65].toString(), result[10].toString(), (BigDecimal)result[9]
		   )).collect(Collectors.toList());*/
	}
}
