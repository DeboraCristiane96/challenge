package com.compass.challenge.domain.business.impls;

import com.compass.challenge.api.mappers.CommentsConverter;
import com.compass.challenge.domain.business.services.CommentsService;
import com.compass.challenge.domain.entitys.Comments;
import com.compass.challenge.domain.repositorys.CommentsRepository;
import com.compass.challenge.exceptions.ResourceNotFoundException;
import com.compass.challenge.payload.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private CommentsRepository repository;
    private final CommentsConverter converter;
    @Override
    public CommentDto createComment(CommentDto commentDto) {
        return repository.save(commentDto);

    }

    @Override
    public List<CommentDto> getCommentsByCommentId(Long id) {
        try {
            CommentDto commentDto = repository.findAllById(id);
            return (List<CommentDto>) repository.save(commentDto);
        }catch (Exception e){
            throw new RuntimeException("Erro ao salvar Commentário com id"+ id, e);
        }
    }

    @Override
    public List<CommentDto> getAllComments() {
        try {
            return converter.toListDto(repository.findAll());
        }catch (Exception e) {
            throw new RuntimeException(format("Erro ao buscar todos os comentários"), e);
        }
    }

    @Override
    public CommentDto updateComment(Long commentId, CommentDto commentRequest) throws ResourceNotFoundException {
        Comments comments = repository.getReferenceById(commentId);
        //postId must be a number between 1 and 100.
        if (commentId < 1 || commentId > 100) {
            throw new ResourceNotFoundException();
        }
        comments.setName(commentRequest.getName());
        comments.setEmail(commentRequest.getEmail());
        comments.setBody(commentRequest.getBody());
        CommentDto commentDto = converter.entitytoDTO(comments);
        return repository.save(commentDto);
    }

    @Override
    public void deleteComment(Long commentId) {
        CommentDto commentDto = repository.findAllById(commentId);
        Comments comments = converter.toEntity(commentDto);
        repository.delete(comments);
    }
}
