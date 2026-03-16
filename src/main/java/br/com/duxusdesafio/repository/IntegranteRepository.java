package br.com.duxusdesafio.repository;

import br.com.duxusdesafio.entity.IntegranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegranteRepository extends JpaRepository<IntegranteEntity, Long> {
}
