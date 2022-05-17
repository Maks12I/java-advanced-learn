package ru.maks12i.restClientService.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.maks12i.restClientService.event.model.ClientSpringEvent;


public interface EventRepos extends CrudRepository<ClientSpringEvent, Long> {
}
