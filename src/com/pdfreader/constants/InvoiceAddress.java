package com.pdfreader.constants;

public class InvoiceAddress {
	private String soldTo;
	private String shiftTo;
	private String remitTo;
	private String invoiceNum;

	public InvoiceAddress(String soldTo, String shiftTo, String remitTo, String invoiceNum) {
		this.shiftTo = shiftTo;
		this.soldTo = soldTo;
		this.remitTo = remitTo;
		this.invoiceNum = invoiceNum;

	}

	public String getSoldTo() {
		return soldTo;
	}

	public String getShiftTo() {
		return shiftTo;
	}

	public String getRemitTo() {
		return remitTo;
	}

	public String getInvoiceNum() {
		return invoiceNum;
	}

}
