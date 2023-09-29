package com.processos.demo.repository;
import com.processos.demo.model.Processo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProcessoRepository extends CrudRepository<Processo, Long> {
    Processo findByNumero(String numero);
}
