package ru.maks12i.restClientService.exceptions;


public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException() {
        super("Клиент не найден.");
    }
}
