package ru.maks12i.restClientService.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.maks12i.restClientService.model.domain.Client;


public interface ClientRepos extends PagingAndSortingRepository<Client, Long> {
    Page<Client> findByLastName(String lastName, Pageable page);
    Client findByEmail(String email);
    Client findByContactNumber(String number);
}
