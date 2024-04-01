package org.electricaltrainingalliance.tickets.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.electricaltrainingalliance.validations.ValidEntity;

@Entity
@Table(name = "red_hat_tickets", indexes = {
    @Index(name = "uk_unique_case", columnList = "case_id", unique = true),
    @Index(name = "ix_case_status", columnList = "case_status", unique = false)
})
@NamedQueries({
    @NamedQuery(name = RHTicket.findByStatus, query = "SELECT t FROM  RHTicket t WHERE t.caseStatus = :status"),
    @NamedQuery(name = RHTicket.getAll, query = "SELECT t FROM  RHTicket t")
})
public class RHTicket implements Serializable, ValidEntity {

	private static final long serialVersionUID = 1L;

	private static final String PREFIX = "tickets.entity.";
	public static final String findByStatus = PREFIX + "findByStatus";
	public static final String getAll = PREFIX + "getAll";

	@Id
	@Column(name = "case_id")
	protected Integer caseId;

	@Column(name = "case_name", nullable = false)
	private String caseName;

	@Column(name = "case_status", nullable = false)
	private String caseStatus;

	public RHTicket() {
		this.caseId = -1;
	}

	public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

	@Override
	public int hashCode() {
		return Objects.hash(caseId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RHTicket other = (RHTicket) obj;
		return Objects.equals(caseId, other.caseId);
	}

    @Override
	@Transient
	@JsonbTransient
	public boolean isValid() {
		// Implement Entity business rules for Validity Checks
        // Case ID must be positive and not zero
        if (this.caseId <= 0) return false;
        // Case Name is required
		if (this.caseName == null || this.caseName.isEmpty() || this.caseName.isBlank()) return false;
        // Case Status is required
		if (this.caseStatus == null || this.caseStatus.isEmpty() || this.caseStatus.isBlank()) return false;
        return true;
    }

}