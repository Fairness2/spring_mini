package ru.geekbrains.spring_mini.exceptions;

public class StudentExistException extends RuntimeException {
    public StudentExistException(String message) {
        super(message);
    }
}
