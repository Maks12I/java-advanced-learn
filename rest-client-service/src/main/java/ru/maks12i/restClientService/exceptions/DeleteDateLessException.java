package ru.maks12i.restClientService.exceptions;

public class DeleteDateLessException  extends RuntimeException {
    public DeleteDateLessException() {
        super("Дата удаления не может быть раньше даты регистрации.");
    }
}

