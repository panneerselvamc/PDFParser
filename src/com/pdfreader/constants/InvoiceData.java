package com.pdfreader.constants;

public class InvoiceData {
	private String customerPO;
	private String amount;
	private String invoiceNum;
	private String invoiceDate;

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
