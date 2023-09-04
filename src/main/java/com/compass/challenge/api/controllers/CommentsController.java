package com.compass.challenge.api.controllers;

import com.compass.challenge.domain.business.ChallengeApiService;
import com.compass.challenge.domain.business.services.CommentsService;
import com.compass.challenge.exceptions.ResourceNotFoundException;
import com.compass.challenge.payload.CommentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")

public class CommentsController {

    private final ChallengeApiService service;
    private final CommentsService commentsService;

    @Autowired
    public CommentsController(ChallengeApiService service, CommentsService commentsService) {
        this.service = service;
        this.commentsService = commentsService;
    }

    @Operation(summary = "Busca Commentários da API e Salvar", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados")

    })
    @PostMapping("/")
    public ResponseEntity<List<CommentDto>> saveCommentsOfApi() {
        return ResponseEntity.ok(service.findCooments());
    }

    @Operation(summary = "Salva novos Comments", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentário salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os Comentários")
    })
    @PostMapping("/")
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto dto) {
        return ResponseEntity.ok(commentsService.createComment(dto));
    }

    @Operation(summary = "Fazer update de novos Comentários", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os Comentários")
    })
    @PutMapping("/")
    public ResponseEntity<CommentDto> updateComment(@RequestParam("id") Long id, @RequestBody CommentDto dto) throws ResourceNotFoundException {
        return ResponseEntity.ok(commentsService.updateComment(id, dto));
    }

    @Operation(summary = "Delete Commentário", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentário deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar os Comentários")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@RequestParam("id") Long id)  {
        commentsService.deleteComment(id);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Busca todos os Comenários", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commentário salvo com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os Commentários")
    })
    @GetMapping()
    public ResponseEntity<List<CommentDto>> getAllComments() {
        return ResponseEntity.ok(commentsService.getAllComments());
    }
}
