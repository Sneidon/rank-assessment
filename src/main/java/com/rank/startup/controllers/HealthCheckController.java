package com.rank.startup.controllers;

import com.rank.startup.dto.HealthDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@RestController
@RequestMapping("/health-check")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<HealthDto> health(){
        return new ResponseEntity<>(generateHealthCheckObject(), HttpStatus.OK);
    }

    private HealthDto generateHealthCheckObject()  {
        LocalDateTime datetime = LocalDateTime.now();
        return new HealthDto("I am healthy!", datetime.toString());
    }
}
