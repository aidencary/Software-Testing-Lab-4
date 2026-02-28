package com.library.softwaretestinglab4.library.Exceptions;

public class DatabaseFailureException extends Exception{

    public DatabaseFailureException(String message){
        super("Database Failed!" + message);
    }
}
