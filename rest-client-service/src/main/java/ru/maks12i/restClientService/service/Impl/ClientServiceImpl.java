package ru.maks12i.restClientService.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.maks12i.restClientService.exceptions.ClientAlreadyExistsException;
import ru.maks12i.restClientService.exceptions.ClientNotFoundException;
import ru.maks12i.restClientService.exceptions.DeleteDateLessException;
import ru.maks12i.restClientService.mapper.ClientMapper;
import ru.maks12i.restClientService.repository.ClientRepos;
import ru.maks12i.restClientService.event.model.ClientEvent;
import ru.maks12i.restClientService.event.publisher.ClientPublisher;
import ru.maks12i.restClientService.model.domain.Client;
import ru.maks12i.restClientService.model.dto.ClientDto;
import ru.maks12i.restClientService.service.ClientService;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Validated
public class ClientServiceImpl implements ClientService {

    private final ClientRepos clientRepos;
    private final ClientMapper clientMapper;
    private final ClientPublisher clientPublisher;

    @Override
    public String signUpClient(ClientDto clientDto) {
        Client client = clientMapper.toClient(clientDto);
        client.setId(clientRepos.count() + 1);
        client.setSignUpDate(LocalDate.now());
        clientByEmailAndContactNumber(client);
        clientPublisher.publish(client.getId(), ClientEvent.CREATE);
        return "Client created";
    }

    @Override
    public String deleteClient(Long id) {
        Optional<Client> client = clientRepos.findById(id);
        if (!client.isPresent())
            throw new ClientNotFoundException();

        clientRepos.deleteById(id);
        clientPublisher.publish(id, ClientEvent.DELETE);
        return "Клиент с id = " + id + " удалён.";
    }

    @Override
    public String updateClient(@Valid Client client) {
        dateCompare(client.getSignUpDate(), client.getDeleteDate());
        clientByEmailAndContactNumber(client);
        clientPublisher.publish(client.getId(), ClientEvent.UPDATE);
        return "Данные клиента обновлены.";
    }

    @Override
    public Client dateCompare(LocalDate date1, LocalDate date2) {
        if(date1.isAfter(date2)){
            throw new DeleteDateLessException();
        }
        return null;
    }

    private void clientByEmailAndContactNumber(@Valid Client client) {
        Client clientByEmail = clientRepos.findByEmail(client.getEmail());
        if (clientByEmail != null && client.getId() != (clientByEmail.getId()))
            throw new ClientAlreadyExistsException("email");

        Client clientByContactNumber = clientRepos.findByContactNumber(client.getContactNumber());
        if (clientByContactNumber != null && !client.getId().equals(clientByContactNumber.getId()))
            throw new ClientAlreadyExistsException("контактный телефон");
        clientRepos.save(client);
    }

    @Override
    public Client getClient(Long id) {
        Optional<Client> client = clientRepos.findById(id);
        if (!client.isPresent())
            throw new ClientNotFoundException();
        return client.get();
    }

    @Override
    public Page<Client> getClientsOnPage(Pageable page) {
        return clientRepos.findAll(page);
    }

    @Override
    public Page<Client> getClientsByLastName(String lastName, Pageable page) {
        return clientRepos.findByLastName(lastName, page);
    }

    @Override
    public Page<Client> getClientsByMail(String emailDomain, Pageable page) {
        List<Client> collect =
                clientRepos
                        .findAll(page)
                        .stream()
                        .filter(client ->
                                client.getEmail().split("@")[1].equals(emailDomain))
                        .collect(Collectors.toList());
        Page<Client> clients = new PageImpl<>(collect, page, collect.size());
        return clients;
    }
}
