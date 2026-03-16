package br.com.duxusdesafio.config.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperConfig {

    private static ModelMapper modelMapper;

    public static ModelMapper getInstance() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();

            configureModelMapper();
        }

        return modelMapper;
    }

    private static void configureModelMapper() {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }
}
