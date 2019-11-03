package com.main;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
@Component
public class TimeProvider {

    public LocalDateTime localDateTime() {
        return LocalDateTime.now();
    }

    public LocalDate localDate() {
        return LocalDate.now();
    }
}
