package com.blog.demo.services.dateFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by damiass on Oct, 2019
 */
public abstract class FormatDate {


    private DateTimeFormatter dayMonthYear = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String dateString;
    private Date date;
    private DateFormat dateFormat;

    protected LocalDate formatDateToDayMonthYear() {
        dateString = dayMonthYear.format(LocalDateTime.now());
        return LocalDate.parse(dateString, dayMonthYear);
    }

    protected String formatDateToDayMonthYearHours() {
        date = new Date();
        dateString = "MM-yyy hh:mm:ss a";
        dateFormat = new SimpleDateFormat(dateString);
        return dateFormat.format(date);
    }


}
