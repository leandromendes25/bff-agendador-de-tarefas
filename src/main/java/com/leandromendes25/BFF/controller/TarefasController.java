package com.leandromendes25.BFF.controller;

import com.leandromendes25.BFF.business.TarefasService;
import com.leandromendes25.BFF.business.dto.in.TarefasDTORequest;
import com.leandromendes25.BFF.business.dto.out.TarefasDTOResponse;
import com.leandromendes25.BFF.infrastructure.config.SecurityConfig;
import com.leandromendes25.BFF.infrastructure.enums.StatusNotificacaoEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("tarefas")
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
@Tag(name = "Tarefas", description = "Cadastro de tarefas")
public class TarefasController {
    private final TarefasService tarefasService;
    @PostMapping
    @Operation(summary = "Salvar Tarefas", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(name="Authorization",required=false) String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }
 @GetMapping("/eventos")
 @Operation(summary = "Busca tarefas ṕor periodo", description = "Cria um novo usuário")
 @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
 @ApiResponse(responseCode = "500", description = "Erro no servidor")
 @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name="Authorization",required=false) String token){
    return ResponseEntity.ok(tarefasService.buscaTarefaAgendadaPorPeriodo(dataInicial, dataFinal, token));
 }
 @GetMapping
 @Operation(summary = "Busca tarefas ṕor email de usuário", description = "Busca tarefas de usuário")
 @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
 @ApiResponse(responseCode = "500", description = "Erro no servidor")
 @ApiResponse(responseCode = "403", description = "Email não encontrado")
 @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
 public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasEmail(@RequestHeader(name="Authorization",required=false) String token){
        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
 }
 @DeleteMapping
 @Operation(summary = "Deleta tarefas por id", description = "deleta tarefas cadastradas por id")
 @ApiResponse(responseCode = "200", description = "Tarefas deletadas")
 @ApiResponse(responseCode = "500", description = "Erro no servidor")
 @ApiResponse(responseCode = "403", description = "Tarefa Id não encontrada")
 @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
 public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id, @RequestHeader(name="Authorization",required=false) String token){
        tarefasService.deletaTarefaPorId(id, token);
        return ResponseEntity.noContent().build();
 }
 @PutMapping
 @Operation(summary = "Atauliza dados de tarefas", description = "Atualiza dados de tarefa cadastrada")
 @ApiResponse(responseCode = "200", description = "Tarefas atualizada")
 @ApiResponse(responseCode = "500", description = "Erro no servidor")
 @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
 public ResponseEntity<TarefasDTOResponse> updateDeTarefas(@RequestBody TarefasDTORequest dto, @RequestParam("id") String id,
                                                           @RequestHeader(name="Authorization",required=false) String token){
     return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));

 }
 @PatchMapping
 @Operation(summary = "Altera status de tarefa", description = "altera status de tarefas cadastrada")
 @ApiResponse(responseCode = "200", description = "Atualiza dados de tarefas ")
 @ApiResponse(responseCode = "500", description = "Erro no servidor")
 @ApiResponse(responseCode = "403", description = "Tarefa Id não encontrada")
 @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestParam("id") String id, @RequestHeader(name="Authorization",required=false) String token){
    return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
 }
}
