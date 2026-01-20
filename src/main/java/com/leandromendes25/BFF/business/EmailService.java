package com.leandromendes25.BFF.business;

import com.leandromendes25.BFF.business.dto.out.TarefasDTOResponse;
import com.leandromendes25.BFF.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private EmailClient client;

    public void enviaEmail(TarefasDTOResponse dto) {
        client.enviarEmail(dto);

    }
}
