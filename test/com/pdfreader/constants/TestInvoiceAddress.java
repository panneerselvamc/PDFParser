package com.pdfreader.constants;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestInvoiceAddress {
	
	@Test
	public void testInvoiceAddress(){
		InvoiceAddress invoiceAddress=new InvoiceAddress("Temp Sold To", "Temp Ship To", "Temp Remit To", "Temp Invoice Num");
		assertEquals(invoiceAddress.getSoldTo(),"Temp Sold To");
		assertEquals(invoiceAddress.getShiftTo(),"Temp Ship To");
		assertEquals(invoiceAddress.getRemitTo(),"Temp Remit To");
		assertEquals(invoiceAddress.getInvoiceNum(),"Temp Invoice Num");
		
		assertNotEquals(invoiceAddress.getSoldTo()," ");
		
		assertEquals(true, invoiceAddress instanceof InvoiceAddress);
	}

}
