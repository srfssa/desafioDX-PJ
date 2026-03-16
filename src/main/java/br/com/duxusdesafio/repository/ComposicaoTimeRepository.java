package br.com.duxusdesafio.repository;

import br.com.duxusdesafio.entity.ComposicaoTimeEntity;
import br.com.duxusdesafio.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComposicaoTimeRepository extends JpaRepository<ComposicaoTimeEntity, Long> {

    @Query
    ComposicaoTimeEntity findByTime(TimeEntity timeEntity);

    @Query
    List<ComposicaoTimeEntity> findAllByTime(TimeEntity timeEntity);
}
