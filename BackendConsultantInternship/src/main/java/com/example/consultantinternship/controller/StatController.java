package com.example.consultantinternship.controller;

import com.example.consultantinternship.service.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StatController {
    private final StatService statService;
    @PostMapping("heartbeat")
    public void heartbeat(@RequestBody String json) {
        this.statService.handleHeartbeat(json);
    }

    @PostMapping("page-visit")
    public void pageVisit(@RequestBody String json) {
        this.statService.handlePageVisit(json);
    }
}
