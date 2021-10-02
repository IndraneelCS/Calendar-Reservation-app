package com.accenture.calendar;

import java.sql.SQLOutput;
import java.util.*;

public class App {

    static final String FAILURE = "failure";

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            String inputLine = "";
            while (sc.hasNext()) {

                inputLine = sc.nextLine();
                String[] input = inputLine.split(" ");

                Command cmd = DataStore.commands.get(input[0]);
                if (cmd != null)
                    System.out.println(cmd.invoke(input));
                else
                    System.err.println(FAILURE);
            }
            sc.close();
            System.out.println("Users:: " + DataStore.userMap);
            System.out.println("Events:: " + DataStore.eventMap);
        }catch (Exception e){
            System.out.println("Main exception");
            System.out.println(FAILURE);
        }
    }
}