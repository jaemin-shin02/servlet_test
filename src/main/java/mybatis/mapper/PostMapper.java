package main.java.mybatis.mapper;

import main.java.domain.Post;

import java.util.List;

public interface PostMapper {
    void insertPost(Post post);
    void updatePost(Post post);
    void deletePost(Long postId);

    Post findById(Long postId);

    List<Post> findByMemberId(Long memberId);

}
