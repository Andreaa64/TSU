package main.java.it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductModel {
	public void doSave(ProductBean product) throws SQLException;

	public boolean doDelete(int Idp) throws SQLException;

	public ProductBean doRetrieveByKey(int Idp) throws SQLException;
	
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException;
	
	public void doOrdine(Cart c) throws SQLException;
}
