package ru.maks12i.restClientService.mapper.customMapper;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import ru.maks12i.restClientService.model.domain.Gender;


@Named("CustomMapper")
@Component
public class CustomMapper {

    @Named("genderToEnumGender")
    public  Gender genderToEnumGender(String gender) {
        return Enum.valueOf(Gender.class, gender.toUpperCase());
    }

    @Named("fioToThirdNameOrNull")
    public  String fioToThirdNameOrNull(String fio){
        String[] split = fio.split("\\s+");
        String thirdName = null;

        try {
            thirdName = split[3];
        } finally {
            return thirdName;
        }

    }
}
