package com.example.cms.repository;

import com.example.cms.entity.ComplaintEntity;
import com.example.cms.helper.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepo extends JpaRepository<ComplaintEntity,Integer> {

    Long countByStatus(Status status);
}
