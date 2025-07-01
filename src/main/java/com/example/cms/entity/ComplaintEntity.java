package com.example.cms.entity;
import com.example.cms.helper.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="complaint")
public class ComplaintEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int complaintId;

    private String customerName;
    private String issueDescription;
    private LocalDate raisedOn=LocalDate.now();
    private LocalDate resolvedOn;
    private Long resolutionDurationDays;

    @Enumerated(EnumType.STRING)
    private Status status=Status.OPEN;


    public ComplaintEntity() {
    }

    public ComplaintEntity(int complaintId, String customerName, String issueDescription, LocalDate raisedOn, LocalDate resolvedOn, Long resolutionDurationDays, Status status) {
        this.complaintId = complaintId;
        this.customerName = customerName;
        this.issueDescription = issueDescription;
        this.raisedOn = raisedOn;
        this.resolvedOn = resolvedOn;
        this.resolutionDurationDays = resolutionDurationDays;
        this.status = status;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public LocalDate getRaisedOn() {
        return raisedOn;
    }

    public void setRaisedOn(LocalDate raisedOn) {
        this.raisedOn = raisedOn;
    }

    public LocalDate getResolvedOn() {
        return resolvedOn;
    }

    public void setResolvedOn(LocalDate resolvedOn) {
        this.resolvedOn = resolvedOn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getResolutionDurationDays() {
        return resolutionDurationDays;
    }

    public void setResolutionDurationDays(Long resolutionDurationDays) {
        this.resolutionDurationDays = resolutionDurationDays;
    }
}
