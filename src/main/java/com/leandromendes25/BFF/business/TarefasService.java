package com.leandromendes25.BFF.business;

import com.leandromendes25.BFF.business.dto.in.TarefasDTORequest;
import com.leandromendes25.BFF.business.dto.out.TarefasDTOResponse;
import com.leandromendes25.BFF.infrastructure.client.TarefasClient;
import com.leandromendes25.BFF.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {
    private TarefasClient client;

    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto){
        return client.gravarTarefas(dto,token);
    }
    public List<TarefasDTOResponse> buscaTarefaAgendadaPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token){
    return client.buscaListaDeTarefasPorPeriodo(dataInicial,dataFinal, token);
    }
    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token){
    return client.buscaTarefasEmail(token);
    }
    public void deletaTarefaPorId(String id, String token){
    deletaTarefaPorId(id, token);
    }
    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token){
    return client.alteraStatusNotificacao(status, id, token);
    }
    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token){
        return client.updateDeTarefas(dto, id, token );
    }
}
