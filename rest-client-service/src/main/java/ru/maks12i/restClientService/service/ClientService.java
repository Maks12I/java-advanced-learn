package ru.maks12i.restClientService.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.maks12i.restClientService.model.domain.Client;
import ru.maks12i.restClientService.model.dto.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.time.LocalDate;


@Validated
public interface ClientService {

    String signUpClient(ClientDto clientDto);
    String deleteClient(Long id);
    String updateClient(@Valid Client client);
    Client dateCompare(LocalDate signUpDate, LocalDate deleteDate);
    Client getClient(Long id);
    Page<Client> getClientsOnPage(Pageable page);
    Page<Client> getClientsByLastName(String lastName, Pageable page);
    Page<Client> getClientsByMail(String emailDomain, Pageable page);

}
