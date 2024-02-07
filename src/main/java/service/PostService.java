package main.java.service;

import main.java.domain.Post;
import main.java.mybatis.mapper.PostMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PostService {
    private final SqlSessionFactory sqlSessionFactory;

    public PostService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void addPost(Post post) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PostMapper postMapper = session.getMapper(PostMapper.class);
            postMapper.insertPost(post);
            session.commit();
        }
    }

//    public void updatePost(Post post) {
//        try (SqlSession session = sqlSessionFactory.openSession()) {
//            PostMapper postMapper = session.getMapper(PostMapper.class);
//
//
//            session.commit();
//        }
//    }

    public void deletePost(Long postId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PostMapper postMapper = session.getMapper(PostMapper.class);
            postMapper.deletePost(postId);

            session.commit();
        }
    }

    public Post findById(Long postId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PostMapper postMapper = session.getMapper(PostMapper.class);
            return postMapper.findById(postId);
        }
    }

    public List<Post> findByMemberId(Long memberId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PostMapper postMapper = session.getMapper(PostMapper.class);
            return postMapper.findByMemberId(memberId);
        }
    }
}
