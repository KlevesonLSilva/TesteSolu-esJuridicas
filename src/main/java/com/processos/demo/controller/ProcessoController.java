package com.processos.demo.controller;

import com.processos.demo.DTO.ProcessoDTO;
import com.processos.demo.DTO.ReuDTO;
import com.processos.demo.model.Processo;
import com.processos.demo.service.ProcessoService;
import net.bytebuddy.asm.MemberSubstitution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/processo")
public class ProcessoController {
    @Autowired
    private ProcessoService processoService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Processo> salvarProcesso(@RequestBody ProcessoDTO processoDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(processoService.salvarProcesso(processoDTO));
    }

    @GetMapping(value = "/{numeroProcesso}")
    public ResponseEntity<Processo> buscarProcesso(@PathVariable final String numeroProcesso){
        Processo processo = processoService.buscarProcesso(numeroProcesso);
        if(processo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(processo);
    }

    @DeleteMapping(value = "/{numeroProcesso}")
    public ResponseEntity deletarProcesso(@PathVariable final String numeroProcesso){

        if(processoService.deletarProcesso(numeroProcesso)){
            return ResponseEntity.ok().build();
        }

         return ResponseEntity.notFound().build();

    }

    @PutMapping(value = "/{numeroProcesso}")
    public ResponseEntity<Processo> adicionarReu(@RequestBody ReuDTO reuDTO,
                                              @PathVariable final String numeroProcesso){
        Processo processo = processoService.adicionarReu(numeroProcesso, reuDTO);
        if(processo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(processo);
    }


}
