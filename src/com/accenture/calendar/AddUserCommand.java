package com.accenture.calendar;

class AddUserCommand implements Command {

    @Override
    public String invoke(String[] input) {
        if (!validateInput(input) || !DataStore.addUser(input[1]))
            return FAILURE;
        return SUCCESS;
    }

    @Override
    public Boolean validateInput(String[] input) {
        if (input.length != 2)
            return Boolean.FALSE;
        return Boolean.TRUE;
    }

}
