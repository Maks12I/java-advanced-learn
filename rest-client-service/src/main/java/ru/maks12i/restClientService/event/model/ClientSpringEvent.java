package ru.maks12i.restClientService.event.model;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.support.AbstractApplicationContext;
import ru.maks12i.restClientService.service.Impl.ClientServiceImpl;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "event_table")
public class ClientSpringEvent extends ApplicationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    @Enumerated(EnumType.STRING)
    private ClientEvent event;
    private LocalDate eventDate;

    public ClientSpringEvent(Object source, Long clientId, ClientEvent event) {
        super(source);
        this.clientId = clientId;
        this.event = event;
        this.eventDate = LocalDate.now();
    }

    public ClientSpringEvent() {
        super(ClientServiceImpl.class);
    }

}
