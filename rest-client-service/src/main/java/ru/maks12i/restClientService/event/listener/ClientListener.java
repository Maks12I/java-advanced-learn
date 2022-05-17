package ru.maks12i.restClientService.event.listener;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.maks12i.restClientService.event.repository.EventRepos;
import ru.maks12i.restClientService.event.model.ClientSpringEvent;


@Component
@AllArgsConstructor
public class ClientListener implements ApplicationListener<ClientSpringEvent> {

    private final EventRepos eventRepos;

    @Override
    public void onApplicationEvent(ClientSpringEvent clientSpringEvent) {
        eventRepos.save(clientSpringEvent);
    }

}
