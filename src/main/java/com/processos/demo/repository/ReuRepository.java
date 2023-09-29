package com.processos.demo.repository;
import com.processos.demo.model.Reu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ReuRepository extends CrudRepository<Reu, Long> {

}
