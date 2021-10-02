package com.accenture.calendar;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

class CreateEventCommand implements Command {

    private Integer startTime = null;
    private LocalDate date = null;
    private Integer duration = null;
    private Integer noOfUsers = null;

    @Override
    public String invoke(String[] input) {
        if(!validateInput(input))
            return FAILURE;

        List<String> users = new ArrayList<>();

        for(int i = 0; i < noOfUsers; i++){
            users.add(input[5+i]);
        }

        DataStore.addEvent(date,startTime, startTime+duration-1, users);

        for(String user : users){
            DataStore.addEventToUser(user, date, startTime, startTime + duration - 1, users);
        }

        return SUCCESS;
    }

    @Override
    public Boolean validateInput(String[] input) {
        if(input.length < 6)
            return Boolean.FALSE;

        try{
             date = LocalDate.parse(input[1]);
             startTime = Integer.parseInt(input[2]);
             duration = Integer.parseInt(input[3]);
             noOfUsers = Integer.parseInt(input[4]);
        } catch (DateTimeParseException | NumberFormatException e){
            System.out.println("Caught exception");
            return Boolean.FALSE;
        }

        if(date.isBefore(LocalDate.now()) || startTime < 0 || duration <= 0 || noOfUsers <= 0 || startTime >= 1440 || startTime + duration > 1440 || 5 + noOfUsers != input.length)
            return Boolean.FALSE;

        for(int i = 0; i < noOfUsers; i++){
            if(!DataStore.userMap.containsKey(input[5+i]) || !DataStore.isUserAvailable(input[5+i], date, startTime, startTime+duration-1)){
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }
}
