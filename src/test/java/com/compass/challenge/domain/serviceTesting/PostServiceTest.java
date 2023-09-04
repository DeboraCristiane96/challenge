package com.compass.challenge.domain.serviceTesting;

import com.compass.challenge.domain.business.services.PostService;
import com.compass.challenge.domain.entitys.Post;
import com.compass.challenge.domain.entitys.QueryBuilder;
import com.compass.challenge.domain.repositorys.PostRepository;
import com.compass.challenge.payload.PostDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.compass.challenge.common.PostConstants.POST;
import static com.compass.challenge.common.PostConstants.POST_INVALID;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

public class PostServiceTest {

    @InjectMocks
    private PostService postService;
    @Mock
    private PostRepository postRepository;
    @Test
    public void createPost_withValidData_ReturnsPost(){

        // Arrange
        when(postRepository.save(POST)).thenReturn(POST);

        // Act
        Post sut =  postService.savePost(POST);
        // Assert
        assertThat(sut).isEqualTo(POST);
    }

    @Test
    public void createPost_withInvalidData_ReturnsPost_TrowsException() {

        // Arrange
        when(postRepository.save(POST_INVALID)).thenThrow(RuntimeException.class);

        // Assert
        assertThatThrownBy(() ->
               postService.savePost(POST_INVALID)).isInstanceOf(RuntimeException.class);
    }
    @Test
    public void getPost_ByExistingID_ReturnsPost(){

        // Arrange
        when(postRepository.findById(POST.getId())).thenReturn(Optional.of(POST));

        // Act
        Optional<Post>  sut = postService.findById(POST.getId());

        //Assert
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(POST);
    }
    @Test
    public void getPost_ByUnexistingId_ReturnEmpty(){

        // Arrange
        when(postRepository.findById(POST.getId())).thenReturn(Optional.empty());

        // Act
        Optional<Post> sut = postService.findById(POST.getId());

        //Assert
        assertThat(sut).isEmpty();
    }

    @Test
    public void listPosts_ReturnsAllPosts() {
        List<Post> postList = new ArrayList<>() {
            {
                add(POST);
            }
        };
        Example<Post> query = QueryBuilder.makeQuery(new Post());
        when(postRepository.findAll(query)).thenReturn(postList);

        List<PostDto> sut = postService.getAllPosts();

        assertThat(sut.get(0)).isEqualTo(POST);
    }

    @Test
    public void removePosts_WithExistingId_doesNotThrowAnyException() {
        assertThatCode(() -> postService.deletePost("1")).doesNotThrowAnyException();
    }

    @Test
    public void removePost_WithUnexistingId_ThrowsException(){

        doThrow(new RuntimeException()).when(postRepository).deleteById("9");

        assertThatThrownBy(() -> postService.deletePost("9")).isInstanceOf(RuntimeException.class);
    }
}
