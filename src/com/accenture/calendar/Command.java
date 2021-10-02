package com.accenture.calendar;

public interface Command {

    String FAILURE = "failure";
    String SUCCESS = "success";

    String invoke(String[] input);
    Boolean validateInput(String[] input);

}
