package org.electricaltrainingalliance.tickets.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class RHTicketTest {
    
    private RHTicket cut;
    	
	@Test
	public void can_create_valid_ticket() {
		this.cut = new RHTicket();
		cut.setCaseId(8675309);
		cut.setCaseName("Jenny's number is not working");
        cut.setCaseStatus("Ghosted");
		Assertions.assertTrue(cut.isValid());
	}

    @Test
    public void missing_data_is_not_valid() {
		this.cut = new RHTicket();
        // missing everything
		Assertions.assertFalse(cut.isValid());
		cut.setCaseId(8675309);
        // missing name and stauts
		Assertions.assertFalse(cut.isValid());
		cut.setCaseName("Jenny's number is not working");
        // missing status
		Assertions.assertFalse(cut.isValid());
        cut.setCaseStatus("Ghosted");
        // all good
		Assertions.assertTrue(cut.isValid());
    }

}
