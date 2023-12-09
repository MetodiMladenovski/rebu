package com.example.uber.repository;

import com.example.uber.model.Request;
import com.example.uber.model.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RequestRepository extends JpaRepository<Request, UUID> {

}
