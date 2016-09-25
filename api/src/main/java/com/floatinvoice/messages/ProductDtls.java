package com.floatinvoice.messages;

public class ProductDtls extends BaseMsg{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int productId;
	
	
	private String productName;
	
	private String productDescription;
	
	private int productType;
	
	private String productOwnerAcro;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public String getProductOwnerAcro() {
		return productOwnerAcro;
	}

	public void setProductOwnerAcro(String productOwnerAcro) {
		this.productOwnerAcro = productOwnerAcro;
	}
	
	

}
