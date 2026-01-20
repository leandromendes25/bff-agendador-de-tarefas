package com.leandromendes25.BFF.infrastructure.client;

import com.leandromendes25.BFF.business.dto.in.TarefasDTORequest;
import com.leandromendes25.BFF.business.dto.out.TarefasDTOResponse;
import com.leandromendes25.BFF.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {
    @PostMapping
    void enviarEmail(@RequestBody TarefasDTOResponse dto);
}
