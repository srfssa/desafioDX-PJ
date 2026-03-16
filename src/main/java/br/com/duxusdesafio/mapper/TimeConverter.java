package br.com.duxusdesafio.mapper;

import br.com.duxusdesafio.config.mapper.ModelMapperConfig;
import br.com.duxusdesafio.entity.IntegranteEntity;
import br.com.duxusdesafio.entity.TimeEntity;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TimeConverter {

    public static Time toDto(TimeEntity entity) {
        if (entity == null) return null;
        LocalDate date = LocalDate.parse(entity.getData().toString());

        return new Time(date);
    }

    public static TimeEntity toEntity(Time dto) {
        if (dto == null) return null;
        System.out.println("Data: " + dto.getData());
        LocalDate date = LocalDate.parse(dto.getData().toString());

        return new TimeEntity(dto.getId(), date);

//        return modelMapper.map(dto, TimeEntity.class);
    }

    public static List<Time> toDtoList(List<TimeEntity> entities) {
        if (entities == null) return Collections.emptyList();
        List<Time> dtos = new ArrayList<>();
        entities.forEach(e -> {
            Time dto = new Time(e.getId(), e.getData());
            dtos.add(dto);
        });

        return dtos;
    }

    public static List<TimeEntity> toEntityList(List<Time> dtos) {
        if (dtos == null) return Collections.emptyList();

        return dtos.stream().map(TimeConverter::toEntity).collect(Collectors.toList());
    }


}
