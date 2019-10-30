package com.blog.demo.services.dateFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by damiass on Oct, 2019
 */
public abstract class FormatDate {

    private LocalDate localDate;
    private DateTimeFormatter dayMonthYear = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String dateString;

    public LocalDate formatDateToDayMonthYear() {
        dateString = dayMonthYear.format(LocalDateTime.now());
        return LocalDate.parse(dateString, dayMonthYear);
    }



}
