package com.processos.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.processos.demo.DTO.ProcessoDTO;
import com.processos.demo.DTO.ReuDTO;
import com.processos.demo.model.Processo;
import com.processos.demo.service.ProcessoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProcessoControllerTest extends AbstractControllerTest{

    @InjectMocks
    private ProcessoController processoController;

    @Mock
    private ProcessoService service;

    private ObjectMapper mapper;

    @Before
    public void before() {
        this.setUp(processoController);
        mapper = new ObjectMapper();
    }

    @Test
    public void deveBuscarProcessos() throws Exception {

        Processo processo = Processo.builder()
                .id(1L)
                .numero("1")
                .build();

        when(service.buscarProcesso(anyString())).thenReturn(processo);

        mockMvc.perform(get("/processo/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public  void deveSalvarProcesso() throws Exception {
        Processo processo = Processo.builder()
                .numero("2")
                .id(1L)
                .build();

        ProcessoDTO processoDTO = ProcessoDTO.builder()
                .numero("2")
                .build();

        when(service.salvarProcesso(any())).thenReturn(processo);

        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(processoDTO);

        mockMvc.perform(post("/processo")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void deveDeletarProcesso() throws Exception {
        when(service.deletarProcesso(anyString())).thenReturn(true);
        mockMvc.perform(delete("/processo/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deveErroProcessoNaoEncontrado() throws Exception {
        when(service.deletarProcesso(anyString())).thenReturn(false);
        mockMvc.perform(delete("/processo/1"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void deveAddReu() throws Exception {

        ReuDTO reuDTO = ReuDTO.builder().build();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(reuDTO);

        when(service.adicionarReu(anyString(), any())).thenReturn(Processo.builder().build());

        mockMvc.perform(put("/processo/1")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deveRetonarProcessoNaoEncontradoAddReu() throws Exception {

        ReuDTO reuDTO = ReuDTO.builder().build();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(reuDTO);

        when(service.adicionarReu(anyString(), any())).thenReturn(null);

        mockMvc.perform(put("/processo/1")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
