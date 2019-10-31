package com.blog.demo.services.dateFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by damiass on Oct, 2019
 */
public abstract class FormatDate {


    private DateTimeFormatter dayMonthYear = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private DateTimeFormatter dayMonthYearHours = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private String dateString;

    protected LocalDate formatDateToDayMonthYear() {
        dateString = dayMonthYear.format(LocalDateTime.now());
        return LocalDate.parse(dateString, dayMonthYear);
    }

    protected LocalDateTime formatDateToDayMonthYearHours() {
        dateString = dayMonthYearHours.format(LocalDateTime.now());
        return LocalDateTime.parse(dateString, dayMonthYearHours);
    }


}
