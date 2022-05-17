package ru.maks12i.restClientService.exceptions;

public class DeleteDateLessException  extends RuntimeException {
    public DeleteDateLessException() {
        super("Дата удаление не может раньше даты регистрации.");
    }
}

