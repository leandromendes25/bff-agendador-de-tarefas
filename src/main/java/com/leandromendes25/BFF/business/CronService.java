package com.leandromendes25.BFF.business;

import com.leandromendes25.BFF.business.dto.in.LoginDTORequest;
import com.leandromendes25.BFF.business.dto.out.TarefasDTOResponse;
import com.leandromendes25.BFF.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;
    @Value("${usuario.senha}")
    private String senha;
    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){
        String token = login(converterParaRequestDTO());
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);
        List<TarefasDTOResponse> listaDeTarefas = tarefasService.buscaTarefaAgendadaPorPeriodo(horaFutura, horaFuturaMaisCinco, token );
        listaDeTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(),token);
        });
    }
    private String login(LoginDTORequest dto){
        return usuarioService.loginUsuario(dto);
    }
    public LoginDTORequest converterParaRequestDTO(){
        return LoginDTORequest.builder().email(email).senha(senha).build();
    }
}
