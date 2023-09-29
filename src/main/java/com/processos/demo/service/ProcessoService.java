package com.processos.demo.service;

import com.processos.demo.DTO.ReuDTO;
import com.processos.demo.model.Processo;
import com.processos.demo.DTO.ProcessoDTO;

public interface ProcessoService {
    Processo salvarProcesso(ProcessoDTO processoDTO);
    Processo buscarProcesso(String numeroProcesso);
    boolean deletarProcesso(String numeroProcesso);
    Processo adicionarReu(String numeroProcesso, ReuDTO reuDTO);
}
