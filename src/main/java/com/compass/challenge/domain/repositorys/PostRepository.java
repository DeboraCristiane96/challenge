package com.compass.challenge.domain.repositorys;

import com.compass.challenge.domain.entitys.Post;
import com.compass.challenge.payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    PostDto getPostById(String id);
    Boolean findByTitle(String title);

}
