package com.accenture.calendar;

import java.util.Scanner;

public class NewApp {

    static final String FAILURE = "failure";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try{

            Integer noOfInputs = sc.nextInt();
            sc.nextLine();
            if(noOfInputs > 0) {
                for (int i = 1; i <= noOfInputs; i++) {
                    String input = sc.nextLine();
                    if (input != null)
                        DataStore.inputs.add(input);
                }
                sc.close();

                for (String inputLine : DataStore.inputs) {
                    String[] input = inputLine.split(" ");
                    Command cmd = DataStore.commands.get(input[0]);
                    if (cmd != null)
                        System.out.println(cmd.invoke(input));
                    else
                        System.err.println(FAILURE);
                }
            }
        }catch(Exception e){
            sc.close();
            System.out.println(FAILURE);
        }
    }
}
