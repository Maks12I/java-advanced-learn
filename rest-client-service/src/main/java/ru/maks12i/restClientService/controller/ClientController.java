package ru.maks12i.restClientService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maks12i.restClientService.model.domain.Client;
import ru.maks12i.restClientService.model.dto.ClientDto;


@RequestMapping("/clientController")
public interface ClientController {

    @PostMapping("/singUp")
    @Operation(description = "Запрос на регистрацию нового клиента.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Запрос выполнен.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Пользователь уже существует"),
            @ApiResponse(responseCode = "406", description = "Ошибка проверки")
    })
    ResponseEntity<String> signUp(@RequestBody ClientDto clientDto);

    @GetMapping("/get/{id}")
    @Operation(description = "Запрос на получение данных клиента по id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Запрос выполнен.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Клиент с таким id не найден.")
    })
    ResponseEntity<Client> getClient(@PathVariable Long id);

    @PutMapping("/update")
    @Operation(description = "Запрос на обновление данных клиента по id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Запрос выполнен.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Пользователь уже существует."),
            @ApiResponse(responseCode = "406", description = "Ошибка проверки.")
    })
    ResponseEntity<String> updateClient(@RequestBody Client client);

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Запрос на удаление клиента по id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Запрос выполнен.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Клиент с таким id не найден.")
    })
    ResponseEntity<String> deleteClient(@PathVariable Long id);


    @GetMapping("/pages/getAllClients")
    @Operation(description = "Запрос на вывод на страницах всех клиентов.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Запрос выполнен.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
    })
    ResponseEntity<Page<Client>> getClientsOnPage(@RequestParam Integer page,
                                                  @RequestParam(defaultValue = "10") Integer size);

    @GetMapping("/pages/clientsBySecondName")
    @Operation(description = "Запрос на вывод на страницах клиентов по их именам.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Запрос выполнен.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
    })
    ResponseEntity<Page<Client>> getClientsByLastName(@RequestParam Integer page,
                                                      @RequestParam(defaultValue = "10") Integer size,
                                                      @RequestParam String secondName);

    @GetMapping("/pages/email")
    @Operation(description = "Запрос на вывод на страницах клиентов по их email домену.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Запрос выполнен.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
    })
    ResponseEntity<Page<Client>> getClientsByMail(@RequestParam Integer page,
                                                  @RequestParam(defaultValue = "10") Integer size,
                                                  @RequestParam String mailDomain);



}
