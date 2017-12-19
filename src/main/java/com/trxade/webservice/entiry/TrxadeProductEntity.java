package com.trxade.webservice.entiry;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;

//call R37MODS.GETSELECT( 01,'9998', '53', '3647025', '*ALL', '', ' ')
@Entity
@NamedStoredProcedureQuery(name = "GETSELECT", procedureName = "R37MODS.GETSELECT", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CMP", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CUST", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "LOC", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "ORDERID", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "SRCHCAT", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "SRCHDATA", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "PREMIER", type = String.class) })
public class TrxadeProductEntity implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="$PRIC")
	private String price;
	
	@Column(name="$PCOD")
	private Integer pcod;

}
