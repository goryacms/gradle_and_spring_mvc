package ru.goryacms.testmvc.util.exception;

public class TestmvcException extends Exception {
    public TestmvcException(String message) {
        super(message, null, true, false);
    }
}