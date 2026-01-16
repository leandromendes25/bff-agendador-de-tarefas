package com.leandromendes25.BFF.infrastructure.client;

import com.leandromendes25.BFF.business.dto.in.TarefasDTORequest;
import com.leandromendes25.BFF.business.dto.out.TarefasDTOResponse;
import com.leandromendes25.BFF.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {
    @PostMapping
    TarefasDTOResponse gravarTarefas(@RequestBody TarefasDTORequest dto,
                                     @RequestHeader(name="Authorization",required=false) String token);

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name="Authorization",required=false) String token);

    @GetMapping
    List<TarefasDTOResponse> buscaTarefasEmail(@RequestHeader(name="Authorization",required=false) String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id, @RequestHeader(name="Authorization",required=false) String token);

    @PutMapping
    TarefasDTOResponse updateDeTarefas(@RequestBody TarefasDTORequest dto, @RequestParam("id") String id, @RequestHeader(name="Authorization",required=false) String token);

    @PatchMapping
    public TarefasDTOResponse alteraStatusNotificacao(
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestParam("id") String id,@RequestHeader(name="Authorization",required=false) String token);
}
