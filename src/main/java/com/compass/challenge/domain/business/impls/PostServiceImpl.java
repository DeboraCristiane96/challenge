package com.compass.challenge.domain.business.impls;

import com.compass.challenge.api.mappers.PostConverter;
import com.compass.challenge.client.response.PostResponse;
import com.compass.challenge.domain.business.services.PostService;
import com.compass.challenge.domain.entitys.Comments;
import com.compass.challenge.domain.entitys.History;
import com.compass.challenge.domain.entitys.Post;
import com.compass.challenge.domain.repositorys.HistoryRepository;
import com.compass.challenge.domain.repositorys.PostRepository;
import com.compass.challenge.enums.PostStatus;
import com.compass.challenge.exceptions.ResourceNotFoundException;
import com.compass.challenge.payload.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostConverter converter;

    @Override
    public Post savePost(Post post)  {
            try {
                return postRepository.save(post);
            }catch (Exception error){
                throw new RuntimeException("Erro ao salvar post" + error);
            }
        }


    public PostDto savePostDto(PostDto dto) {
        try{
            Post entity = converter.toEntity(dto);
            return converter.toDTO(postRepository.save(entity));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar os posts" + e);
        }
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public Boolean findByTitle(String title) {
        try {
            return postRepository.findByTitle(title);
        }catch (Exception e) {
            throw new RuntimeException(format("Erro ao buscar post por titulo", title), e);
        }

    }


    @Override
    public List<PostDto> getAllPosts() {
        try {
            return converter.toListDto(postRepository.findAll());
        }catch (Exception e) {
            throw new RuntimeException(format("Erro ao buscar todos os posts"), e);
        }
    }

    @Override
    public PostDto updatePost(String id, PostDto dto)  {
        try{
            Post entity =postRepository.findById(id).orElseThrow(() -> new RuntimeException("Id não existe no banco de dados"));
            savePost(converter.toEntityUpdate(entity, dto, dto.getId()));
            return postRepository.getPostById(id);
        }catch (Exception e) {
            throw new RuntimeException(format("Erro ao atualizar post"), e);
        }
    }

    @Override
    public void deletePost(String id) {
        try{
            postRepository.deleteById(id);
        }catch (Exception e) {
            throw new RuntimeException(format("Erro ao deletar post por id", id), e);
        }
    }



}
