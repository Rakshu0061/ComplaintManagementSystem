package com.example.cms.service;

import com.example.cms.entity.ComplaintEntity;
import com.example.cms.exception.ComplaintNotFoundException;
import com.example.cms.helper.Status;
import com.example.cms.repository.ComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepo complaintRepo;

    public ComplaintEntity raiseComplaint(ComplaintEntity complaint)
    {
        complaint.setStatus(Status.OPEN);
        complaint.setRaisedOn(LocalDate.now());
        complaint.setCustomerName(complaint.getCustomerName());
        complaint.setIssueDescription(complaint.getIssueDescription());
        return complaintRepo.save(complaint);
    }

    public List<ComplaintEntity> getAllComplaint()
    {
        return  complaintRepo.findAll();
    }

    public ComplaintEntity updateComplaintStatus(int id,Status status)
    {
        ComplaintEntity complaint=complaintRepo.findById(id).orElseThrow(()->new ComplaintNotFoundException("complaint is not found"));
        complaint.setStatus(status);
        if (status==Status.RESOLVED)
        {
            LocalDate resolvedDate=LocalDate.now();
            complaint.setResolvedOn(resolvedDate);

            Long daysToResolve= ChronoUnit.DAYS.between(complaint.getRaisedOn(),resolvedDate);
            complaint.setResolutionDurationDays(daysToResolve);
        }
           return complaintRepo.save(complaint);
    }

    public Map<Status,Long> getStatusByCount()
    {
        Map<Status,Long> map=new HashMap<>();
        for (Status s:Status.values())
        {
            map.put(s, complaintRepo.countByStatus(s));
        }
        return map;


    }

}
