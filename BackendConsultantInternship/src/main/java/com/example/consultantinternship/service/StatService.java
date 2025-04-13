package com.example.consultantinternship.service;

public interface StatService {

    void handleHeartbeat(String json);

    void handlePageVisit(String json);
}
