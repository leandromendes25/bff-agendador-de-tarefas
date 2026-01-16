package com.leandromendes25.BFF.business;

import com.leandromendes25.BFF.business.dto.in.EnderecoDTORequest;
import com.leandromendes25.BFF.business.dto.in.LoginDTORequest;
import com.leandromendes25.BFF.business.dto.in.TelefoneDTORequest;
import com.leandromendes25.BFF.business.dto.in.UsuarioDTORequest;
import com.leandromendes25.BFF.business.dto.out.EnderecoDTOResponse;
import com.leandromendes25.BFF.business.dto.out.TelefoneDTOResponse;
import com.leandromendes25.BFF.business.dto.out.UsuarioDTOResponse;
import com.leandromendes25.BFF.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {
        return client.salvarUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest dto){
        return client.login(dto);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
     return client.buscarUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
    client.deleteUsuario(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto) {
        return client.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTORequest, String token) {
   return client.atualizaEndereco(enderecoDTORequest,idEndereco,token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO) {
    return client.atualizaTelefone(telefoneDTO, idTelefone);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto) {
    return client.cadastraEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto) {
    return client.cadastraTelefone(dto, token);
    }
}
