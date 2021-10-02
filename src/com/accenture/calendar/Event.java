package com.accenture.calendar;

import java.time.LocalDate;
import java.util.List;

class Event {

    LocalDate date;
    Integer startTime;
    Integer endTime;
    List<String> users;

    public Event(LocalDate date, Integer startTime, Integer endTime, List<String> users) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.users = users;
    }

    @Override
    public String toString() {
        return "Event{" +
                "date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", users=" + users +
                '}';
    }
}
