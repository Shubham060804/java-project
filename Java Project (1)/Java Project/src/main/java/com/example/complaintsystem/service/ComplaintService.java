package com.example.complaintsystem.service;

import com.example.complaintsystem.model.Complaint;
import com.example.complaintsystem.model.ComplaintStatus;
import com.example.complaintsystem.model.Department;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ComplaintService {
    private final List<Complaint> complaints = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public List<Complaint> getAll() {
        return new ArrayList<>(complaints);
    }

    public List<Complaint> getByStatus(ComplaintStatus status) {
        return complaints.stream()
                .filter(c -> c.getStatus() == status)
                .collect(Collectors.toList());
    }

    public void submit(Complaint complaint) {
        complaint.setId(nextId.getAndIncrement());
        complaint.setSubmittedAt(LocalDateTime.now());
        if (complaint.getStatus() == null) {
            complaint.setStatus(ComplaintStatus.NEW);
        }
        complaints.add(complaint);
    }

    public Optional<Complaint> getById(Long id) {
        return complaints.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public void updateStatus(Long id, ComplaintStatus status) {
        getById(id).ifPresent(c -> c.setStatus(status));
    }

    public List<Complaint> getByDepartment(Department department) {
        return complaints.stream()
                .filter(c -> c.getDepartment() == department)
                .collect(Collectors.toList());
    }
}
