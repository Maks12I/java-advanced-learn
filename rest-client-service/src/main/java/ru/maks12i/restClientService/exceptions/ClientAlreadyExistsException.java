package ru.maks12i.restClientService.exceptions;


public class ClientAlreadyExistsException extends RuntimeException{
    public ClientAlreadyExistsException(String message) {
        super("Клиент с такими " + message + " уже существует.");
    }
}
