package com.example.complaintsystem.controller;

import com.example.complaintsystem.model.Complaint;
import com.example.complaintsystem.model.ComplaintStatus;
import com.example.complaintsystem.model.Department;
import com.example.complaintsystem.service.ComplaintService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    private final ComplaintService complaintService;

    public WebController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("complaint", new Complaint());
        model.addAttribute("departments", Department.values());
        model.addAttribute("statuses", ComplaintStatus.values());
        return "index";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute Complaint complaint, Model model) {
        complaintService.submit(complaint);
        model.addAttribute("message", "Your complaint has been submitted successfully.");
        model.addAttribute("departments", Department.values());
        model.addAttribute("statuses", ComplaintStatus.values());
        model.addAttribute("complaint", new Complaint());
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model, @RequestParam(required = false) String department) {
        if (department != null && !department.isEmpty()) {
            model.addAttribute("complaints", complaintService.getByDepartment(Department.valueOf(department)));
        } else {
            model.addAttribute("complaints", complaintService.getAll());
        }
        model.addAttribute("departments", Department.values());
        model.addAttribute("statuses", ComplaintStatus.values());
        return "admin";
    }

    @PostMapping("/admin/update/{id}")
    public String updateStatus(@PathVariable Long id, @RequestParam ComplaintStatus status) {
        complaintService.updateStatus(id, status);
        return "redirect:/admin";
    }
}
