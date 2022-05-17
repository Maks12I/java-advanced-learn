package ru.maks12i.restClientService.event.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import ru.maks12i.restClientService.event.model.ClientEvent;
import ru.maks12i.restClientService.event.model.ClientSpringEvent;


@RequiredArgsConstructor
@Component
public class ClientPublisher {
    private final ApplicationEventPublisher publisher;

    public void publish(Long clientId, ClientEvent event) {
        ClientSpringEvent clientSpringEvent = new ClientSpringEvent(this, clientId, event);
        publisher.publishEvent(clientSpringEvent);
    }

}
