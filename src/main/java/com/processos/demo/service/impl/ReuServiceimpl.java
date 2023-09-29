package com.processos.demo.service.impl;

import com.processos.demo.DTO.ReuDTO;
import com.processos.demo.model.Reu;
import com.processos.demo.repository.ReuRepository;
import com.processos.demo.service.ReuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReuServiceimpl implements ReuService {

    @Autowired
    private ReuRepository reuRepository;

    @Override
    public Reu salvarReu(ReuDTO reuDTO) {
        Reu reu = Reu.builder()
                .nome(reuDTO.getNome())
                .build();

        return reuRepository.save(reu);
    }
}
