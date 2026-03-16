package br.com.duxusdesafio.mapper;

import br.com.duxusdesafio.config.mapper.ModelMapperConfig;
import br.com.duxusdesafio.entity.ComposicaoTimeEntity;
import br.com.duxusdesafio.entity.IntegranteEntity;
import br.com.duxusdesafio.entity.TimeEntity;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ComposicaoTimeConverter {

    private ModelMapper modelMapper = ModelMapperConfig.getInstance();

    public ComposicaoTime toDto(ComposicaoTimeEntity entity) {
        if (entity == null) return null;
        ComposicaoTime dto = new ComposicaoTime();
        Time time = new Time();
        time.setId(entity.getTime().getId());
        time.setData(entity.getTime().getData());
        dto.setTime(time);
        Integrante integrante = new Integrante();
        integrante.setId(entity.getIntegrante().getId());
        integrante.setNome(entity.getIntegrante().getNome());
        integrante.setFuncao(entity.getIntegrante().getFuncao());
        integrante.setFranquia(entity.getIntegrante().getFranquia());
        dto.setIntegrante(integrante);

        return dto;
    }

    public ComposicaoTimeEntity toEntity(ComposicaoTime dto) {
        if (dto == null) return null;

        TimeEntity time = new TimeEntity();
        time.setData(dto.getTime().getData());
        IntegranteEntity integrante = new IntegranteConverter().toEntity(dto.getIntegrante());

        return new ComposicaoTimeEntity(time, integrante);
    }

    public List<ComposicaoTime> toDtoList(List<ComposicaoTimeEntity> entities) {
        if (entities == null) return Collections.emptyList();

        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<ComposicaoTimeEntity> toEntityList(List<ComposicaoTime> dtos) {
        if (dtos == null) return Collections.emptyList();

        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }


}
