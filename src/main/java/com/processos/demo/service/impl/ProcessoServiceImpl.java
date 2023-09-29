package com.processos.demo.service.impl;

import com.processos.demo.DTO.ProcessoDTO;
import com.processos.demo.DTO.ReuDTO;
import com.processos.demo.model.Processo;
import com.processos.demo.model.Reu;
import com.processos.demo.repository.ProcessoRepository;
import com.processos.demo.service.ProcessoService;
import com.processos.demo.service.ReuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessoServiceImpl implements ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;
    @Autowired
    private ReuService reuService;

    @Override
    public Processo salvarProcesso(ProcessoDTO processoDTO) {
        Processo processo = Processo.builder()
               .numero(processoDTO.getNumero())
               .build();

         return processoRepository.save(processo);

    }

    @Override
    public Processo buscarProcesso(String numeroProcesso) {

        return processoRepository.findByNumero(numeroProcesso);
    }

    @Override
    public boolean deletarProcesso(String numeroProcesso) {

        Processo processo = processoRepository.findByNumero(numeroProcesso);
        if(processo == null){
            return false;
        }
        processoRepository.delete(processo);
        return true;
    }

    @Override
    public Processo adicionarReu(String numeroProcesso, ReuDTO reuDTO) {
        Processo processo = processoRepository.findByNumero(numeroProcesso);
        if(processo == null) return null;
        Reu reu = reuService.salvarReu(reuDTO);
        processo.setReu(reu);
        return processoRepository.save(processo);
    }
}
