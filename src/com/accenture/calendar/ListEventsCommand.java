package com.accenture.calendar;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

class ListEventsCommand implements Command {

    private LocalDate date = null;

    @Override
    public String invoke(String[] input) {
        if(!validateInput(input))
            return FAILURE;

        for(Event event : DataStore.userMap.get(input[2])) {
            if (event.date.isEqual(date)) {
                String users = " ";
                for (String user : event.users)
                    users = users + user + " ";
                System.out.println(event.startTime + "-" + (event.endTime + 1) + " " + users.trim());
            }
        }
        return "";
    }

    @Override
    public Boolean validateInput(String[] input) {
        if(input.length != 3 || !DataStore.userMap.containsKey(input[2]))
            return Boolean.FALSE;

        try{
            date = LocalDate.parse(input[1]);
        } catch (DateTimeParseException e){
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
