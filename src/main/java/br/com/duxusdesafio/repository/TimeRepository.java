package br.com.duxusdesafio.repository;

import br.com.duxusdesafio.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeRepository extends JpaRepository<TimeEntity, Long> {

    @Query
    List<TimeEntity> findAllByData(LocalDate data);
}
