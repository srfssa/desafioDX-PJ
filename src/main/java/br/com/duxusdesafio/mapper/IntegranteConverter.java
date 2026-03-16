package br.com.duxusdesafio.mapper;

import br.com.duxusdesafio.config.mapper.ModelMapperConfig;
import br.com.duxusdesafio.entity.IntegranteEntity;
import br.com.duxusdesafio.model.Integrante;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IntegranteConverter {

    private ModelMapper modelMapper = ModelMapperConfig.getInstance();

    public Integrante toDto(IntegranteEntity entity) {
        if (entity == null) return null;

        return modelMapper.map(entity, Integrante.class);
    }

    public IntegranteEntity toEntity(Integrante dto) {
        if (dto == null) return null;

        return modelMapper.map(dto, IntegranteEntity.class);
    }

    public List<Integrante> toDtoList(List<IntegranteEntity> entities) {
        if (entities == null) return Collections.emptyList();

        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<IntegranteEntity> toEntityList(List<Integrante> dtos) {
        if (dtos == null) return Collections.emptyList();

        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }


}
