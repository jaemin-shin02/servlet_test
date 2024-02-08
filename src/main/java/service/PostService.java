package main.java.service;

import main.java.domain.Post;
import main.java.dto.PostSearchConditon;
import main.java.mybatis.mapper.PostMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PostService {
    private final SqlSession sqlSession;
    private final PostMapper postMapper;

    public PostService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.postMapper = sqlSession.getMapper(PostMapper.class);
    }

    public void addPost(Post post) {
        postMapper.insertPost(post);
        sqlSession.commit();
    }

    public void deletePost(Long postId) {
        postMapper.deletePost(postId);
        sqlSession.commit();
    }

    public Post findById(Long postId) {
        return postMapper.findById(postId);
    }

    public List<Post> findAll() {
        return postMapper.findAll();
    }

    public List<Post> findByMemberId(Long memberId) {
        return postMapper.findByMemberId(memberId);
    }

    public List<Post> searchPost(PostSearchConditon conditon){
        return postMapper.searchPost(conditon);
    }
}
