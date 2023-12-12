package com.example.spaceitm.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String formatRelative(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(date, now);

        long minutes = duration.toMinutes();
        long hours = duration.toHours();
        long days = duration.toDays();
        long months = duration.toDays() / 30;
        long years = duration.toDays() / 365;

        if (minutes < 60) {
            return minutes + " минут назад";
        } else if (hours < 24) {
            return hours + " часов назад";
        } else if (days < 30) {
            return days + " дней назад";
        } else if (months < 12) {
            return months + " месяцев назад";
        } else {
            return years + " лет назад";
        }
    }

    public static String formatDateTime(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return date.format(formatter);
    }
}

