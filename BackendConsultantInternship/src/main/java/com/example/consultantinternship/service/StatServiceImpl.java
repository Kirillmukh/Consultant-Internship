package com.example.consultantinternship.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {
    private final String SOURCE_DIR;
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH-mm-ss");

    @Override
    public void handleHeartbeat(String json) {
        writeFile("heartbeat", json);
    }

    @Override
    public void handlePageVisit(String json) {
        writeFile("page-visit", json);
    }

    private void writeFile(String prefix, String json) {
        Date date = new Date();
        String time = DATE_FORMAT.format(date);
        String filename = String.format("%s_%s_%s.json", prefix, time, UUID.randomUUID());
        try {
            Files.writeString(Path.of(SOURCE_DIR, filename), json, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            log.error("couldn't write json file", e);
        }
    }
}
