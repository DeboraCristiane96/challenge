package com.compass.challenge.domain.repositorys;

import com.compass.challenge.domain.entitys.Comments;
import com.compass.challenge.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Long> {
    CommentDto findAllById(Long postId);

    CommentDto save(CommentDto commentDto);
}
