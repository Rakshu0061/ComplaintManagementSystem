package com.example.cms.controller;

import com.example.cms.entity.ComplaintEntity;
import com.example.cms.helper.Status;
import com.example.cms.repository.ComplaintRepo;
import com.example.cms.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/raiseComplaint")
    public ResponseEntity<ComplaintEntity> raiseComplaint(@RequestBody ComplaintEntity complaint)
    {
        ComplaintEntity complaint1= complaintService.raiseComplaint(complaint);
        return new ResponseEntity<>(complaint1, HttpStatus.CREATED);

    }
@GetMapping("/getComplaints")
    public ResponseEntity<List<ComplaintEntity>> getAllComplaints()
    {
        List<ComplaintEntity> complaintList=complaintService.getAllComplaint();
        return  ResponseEntity.ok(complaintList);
    }

    @PutMapping("/{id}/updateStatus")
    public ResponseEntity<ComplaintEntity>updateComplaintStatus(@PathVariable int id, @RequestParam Status status)
    {
        ComplaintEntity complaint1=complaintService.updateComplaintStatus(id,status);
        return  ResponseEntity.ok(complaint1);

    }

    @GetMapping("/getStatusCount")
    public Map<Status,Long> getStatusCount()
    {
        return complaintService.getStatusByCount();
    }

}
