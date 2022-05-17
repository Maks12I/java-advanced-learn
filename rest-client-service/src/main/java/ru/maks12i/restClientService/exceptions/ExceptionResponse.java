package ru.maks12i.restClientService.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
public class ExceptionResponse {

    private String message;
    private LocalDateTime dateTime = LocalDateTime.now();

}
