package main.java.mybatis.mapper;

import main.java.domain.Post;
import main.java.dto.PostSearchConditon;

import java.util.List;

public interface PostMapper {
    void insertPost(Post post);
    void updatePost(Post post);
    void deletePost(Long postId);

    Post findById(Long postId);
    List<Post> findAll();

    List<Post> findByMemberId(Long memberId);

    List<Post> searchPost(PostSearchConditon conditon);

}
