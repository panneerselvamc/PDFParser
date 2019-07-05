package com.pdfreader.constants;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestInvoiceData {

	@Test
	public void testInvoiceData(){
		InvoiceData invoiceData=new InvoiceData("11/11/2019", "12123", "$123", "0001");
		
		assertEquals(invoiceData.getCustomerPO(), "12123");
		
		assertEquals(invoiceData.getAmount(), "$123");
		
		assertEquals(invoiceData.getInvoiceDate(), "11/11/2019");
		
		assertEquals(invoiceData.getInvoiceNum(), "0001");
		
		assertEquals(true, invoiceData instanceof InvoiceData);
	}

}
