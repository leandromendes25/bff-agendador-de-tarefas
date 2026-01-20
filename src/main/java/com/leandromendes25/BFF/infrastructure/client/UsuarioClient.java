package com.leandromendes25.BFF.infrastructure.client;

import com.leandromendes25.BFF.business.dto.in.EnderecoDTORequest;
import com.leandromendes25.BFF.business.dto.in.LoginDTORequest;
import com.leandromendes25.BFF.business.dto.in.TelefoneDTORequest;
import com.leandromendes25.BFF.business.dto.in.UsuarioDTORequest;
import com.leandromendes25.BFF.business.dto.out.EnderecoDTOResponse;
import com.leandromendes25.BFF.business.dto.out.TelefoneDTOResponse;
import com.leandromendes25.BFF.business.dto.out.UsuarioDTOResponse;
import com.leandromendes25.BFF.business.dto.out.ViaCepDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {
    @GetMapping("/usuario")
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest loginDTOrequest);

    @DeleteMapping("/{email}")
    void deleteUsuario(@PathVariable String email, @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                            @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(
            @RequestBody EnderecoDTORequest dto, @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token);


    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto, @RequestParam("id") Long id);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto, @RequestHeader("Authorization") String token);


    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto, @RequestHeader("Authorization") String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscarDadosDeCep(@PathVariable("cep") String cep);
}
