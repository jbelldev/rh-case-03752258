package org.electricaltrainingalliance.tickets.boudary;

import java.util.List;

import org.electricaltrainingalliance.tickets.entity.RHTicket;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("tickets")
public class TicketResource {
	
	@Inject
	TicketManager manager;

	@GET
	@Path("{caseId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response find(@PathParam("caseId") Integer caseId) {
		RHTicket ticket = this.manager.findById(caseId);
		if (ticket != null) {
			return Response.ok(ticket).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/findByStatus/{status}")
	public Response findBystatus(@PathParam("status") String status) {
		List<RHTicket> tickets = this.manager.findByStatus(status);
		if (tickets != null && !tickets.isEmpty()) {
			return Response.ok(tickets).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAll() {
		List<RHTicket> profiles = this.manager.getAll();
		if (profiles != null && !profiles.isEmpty()) {
			return Response.ok(profiles).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
    
}
