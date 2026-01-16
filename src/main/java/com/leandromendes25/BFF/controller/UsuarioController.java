package com.leandromendes25.BFF.controller;

import com.leandromendes25.BFF.business.UsuarioService;
import com.leandromendes25.BFF.business.dto.in.EnderecoDTORequest;
import com.leandromendes25.BFF.business.dto.in.LoginDTORequest;
import com.leandromendes25.BFF.business.dto.in.TelefoneDTORequest;
import com.leandromendes25.BFF.business.dto.in.UsuarioDTORequest;
import com.leandromendes25.BFF.business.dto.out.EnderecoDTOResponse;
import com.leandromendes25.BFF.business.dto.out.TelefoneDTOResponse;
import com.leandromendes25.BFF.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro de usuário")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Logar Usuário", description = "Login de usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado sucesso")
    @ApiResponse(responseCode = "400", description = "Credenciais inválida")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public String login(@RequestBody LoginDTORequest usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }
    @GetMapping
    @Operation(summary = "Busca dados de Usuários por Email", description = "Buscar dados de úsuario")
    @ApiResponse(responseCode = "200", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                                    @RequestHeader(name="Authorization",required=false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }
    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar Usuários por Id", description = "Deleta um usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String email,@RequestHeader(name="Authorization",required=false) String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    @Operation(summary = "Atualizar Dados de Usuários", description = "Atualiza dados usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                                   @RequestHeader(name="Authorization",required=false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token,dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereço de usuário", description = "Atualiza endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto, @RequestParam("id") Long id,
                                                                @RequestHeader(name="Authorization",required=false) String token){
    return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone de Usuário", description = "Atualiza telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto, @RequestParam("id") Long id){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto));
    }
    @PostMapping("/endereco")
    @Operation(summary = "Cria endereço de usuário", description = "Cadastra endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto, @RequestHeader(name="Authorization",required=false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }
    @PostMapping("/telefone")
    @Operation(summary = "Cadastra telefone", description = "Cadastra telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto, @RequestHeader(name="Authorization",required=false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }
}
