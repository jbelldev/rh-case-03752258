package org.electricaltrainingalliance.tickets.boudary;

import java.util.List;

import org.electricaltrainingalliance.tickets.entity.RHTicket;
import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class TicketManager {

    private static final Logger LOGGER = Logger.getLogger(TicketManager.class.getName());

    @Inject
    EntityManager em;

	public RHTicket findById(Integer caseId) {
		return this.em.find(RHTicket.class, caseId);
	}

    public List<RHTicket> findByStatus(String status) {
        try {
            return this.em.createNamedQuery(RHTicket.findByStatus, RHTicket.class)
                    .setParameter("status", status).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.DEBUG, e.toString(), e);
            return null;
        }
    }

    public List<RHTicket> getAll() {
        try {
            return this.em.createNamedQuery(RHTicket.getAll, RHTicket.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.DEBUG, e.toString(), e);
        }
        return null;
    }

}
