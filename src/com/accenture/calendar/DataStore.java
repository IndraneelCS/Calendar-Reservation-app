package com.accenture.calendar;

import java.time.LocalDate;
import java.util.*;

public class DataStore {

    public static List<String> inputs = new ArrayList<>();
    public static Map<String, Command> commands = new HashMap<>();
    public static Map<Integer, Event> eventMap = new HashMap<>();
    public static Map<String, List<Event>> userMap = new HashMap<>();

    private static Integer eventId = 1;

    static {
        commands.put("add-user", new AddUserCommand());
        commands.put("create-event", new CreateEventCommand());
        commands.put("show-events", new ListEventsCommand());
        commands.put("suggest-slot",new SuggestSlotCommand());
    }

    public static Boolean addUser(String name){
        name.trim().strip();
        if(name != null && name.length() != 0 && !name.isBlank() && !name.isEmpty()){
            if(!userMap.containsKey(name)) {
                userMap.put(name, null);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public static void addEventToUser(String name, LocalDate date, Integer startTime, Integer endTime, List<String> users){
        List<Event> events = userMap.get(name);
        if(events == null)
            events = new ArrayList<>();

        Event event = new Event(date, startTime, endTime, users);
        events.add(event);

        userMap.replace(name, events);
    }


    public static void addEvent(LocalDate date, Integer startTime, Integer endTime, List<String> users){
        Event event = new Event(date, startTime, endTime, users);
        eventMap.put(eventId, event);
        eventId++;
    }

    public static Boolean isUserAvailable(String name, LocalDate date, Integer startTime, Integer endTime) {

        List<Event> events = userMap.get(name);

        if(events != null) {
            for (Event event : events) {
                if (date.isEqual(event.date)) {
                    if ((startTime >= event.startTime && startTime <= event.endTime)
                            || (endTime >= event.startTime && endTime <= event.endTime))
                        return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }
}