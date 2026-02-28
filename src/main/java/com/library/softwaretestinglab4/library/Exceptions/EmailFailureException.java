package com.library.softwaretestinglab4.library.Exceptions;

public class EmailFailureException extends Exception{

    public EmailFailureException(String message){
        super("Email Failed!" + message);
    }

}
