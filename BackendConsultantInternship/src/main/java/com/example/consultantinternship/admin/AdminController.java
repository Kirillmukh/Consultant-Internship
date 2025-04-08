package com.example.consultantinternship.admin;

import com.example.consultantinternship.admin.payload.AdminQuestionPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/question")
    public String getQuestionFormPage() {
        return "admin/question";
    }

    @PostMapping("/question")
    public String addQuestion(AdminQuestionPayload questionPayload) {
        adminService.addQuestion(questionPayload.toQuestion());
        return "redirect:/admin/question";
    }
}
