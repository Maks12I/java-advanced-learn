package ru.maks12i.restClientService.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.maks12i.restClientService.mapper.customMapper.CustomMapper;
import ru.maks12i.restClientService.model.domain.Client;
import ru.maks12i.restClientService.model.domain.*;
import ru.maks12i.restClientService.model.dto.ClientDto;

import javax.validation.Valid;


@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CustomMapper.class
)
public interface ClientMapper {

    @Mapping(target = "fio", expression = "java(client.getFirstName() + ' ' + client.getLastName() + ' ' + client.getThirdName())")
    ClientDto toDto(Client client);

    String regexp = "\\s+";
    @Mappings({
        @Mapping(target = "firstName", expression = "java(clientDto.getFio().split(regexp)[0])"),
        @Mapping(target = "lastName", expression = "java(clientDto.getFio().split(regexp)[1])"),
        @Mapping(source = "fio", target = "thirdName", qualifiedByName = "fioToThirdNameOrNull"),
        @Mapping(source = "gender", target = "gender", qualifiedByName = "genderToEnumGender"),
        @Mapping(target = "deleteDate", ignore = true)
    })
    Client toClient(ClientDto clientDto);





}
