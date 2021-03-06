package com.example.chenjutsu.calendar1;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table EVENT.
 */
public class EVENT {

    private Long id;
    private Integer year;
    private Integer month;
    private Integer day;
    private String event;
    private String date;
    private Boolean display;

    public EVENT() {
    }

    public EVENT(Long id) {
        this.id = id;
    }

    public EVENT(Long id, Integer year, Integer month, Integer day, String event, String date, Boolean display) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.event = event;
        this.date = date;
        this.display = display;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

}
