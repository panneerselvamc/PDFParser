package com.pdfreader.business;

public class InvoiceData {
	String customerPO;
	String amount;
	String invoiceNum;
	String invoiceDate;

	public InvoiceData(String invoiceDate, String customerPO, String amount, String invoiceNum) {
		this.amount = amount;
		this.invoiceDate = invoiceDate;
		this.customerPO = customerPO;
		this.invoiceNum = invoiceNum;

	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public String getCustomerPO() {
		return customerPO;
	}

	public String getAmount() {
		return amount;
	}

	public String getInvoiceNum() {
		return invoiceNum;
	}

}
