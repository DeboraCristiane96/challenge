package com.compass.challenge.api.controllers;

import com.compass.challenge.domain.business.ChallengeApiService;
import com.compass.challenge.domain.business.services.PostService;
import com.compass.challenge.exceptions.ResourceNotFoundException;
import com.compass.challenge.payload.PostDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final ChallengeApiService service;
    private final PostService postService;
    @Operation(summary = "Busca posts da API e Salvar", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados")

    })
    @PostMapping()
    public ResponseEntity<List<PostDto>> savePostOfApi() {
        return ResponseEntity.ok(service.findPost());
    }

    @Operation(summary = "Salva novos Posts", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os posts")
    })
    @PostMapping("/")
    public ResponseEntity<PostDto> savePost(@RequestBody PostDto dto) {
        return ResponseEntity.ok(postService.savePostDto(dto));
    }

    @Operation(summary = "Fazer update de novos posts", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os post")
    })
    @PutMapping("/")
    public ResponseEntity<PostDto> updatePost(@RequestParam("id") String id, @RequestBody PostDto dto) throws ResourceNotFoundException {
        return ResponseEntity.ok(postService.updatePost(id, dto));
    }

    @Operation(summary = "Delete Post", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar os posts")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@RequestParam("id") String id) {
        postService.deletePost(id);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Busca todos os posts", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post salvo com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os posts")
    })
    @GetMapping()
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
}
