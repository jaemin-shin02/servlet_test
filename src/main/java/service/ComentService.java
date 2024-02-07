package main.java.service;

import main.java.domain.Coment;
import main.java.mybatis.mapper.ComentMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ComentService {
    private final SqlSessionFactory sqlSessionFactory;

    public ComentService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void addComent(Coment coment) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ComentMapper comentMapper = session.getMapper(ComentMapper.class);
            comentMapper.insertComent(coment);
            session.commit();
        }
    }

//    public void updateComent(Post post) {
//        try (SqlSession session = sqlSessionFactory.openSession()) {
//            PostMapper postMapper = session.getMapper(PostMapper.class);
//
//
//            session.commit();
//        }
//    }

    public void deleteComent(Long comentId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ComentMapper comentMapper = session.getMapper(ComentMapper.class);
            comentMapper.deleteComent(comentId);

            session.commit();
        }
    }

    public Coment findById(Long comentId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ComentMapper comentMapper = session.getMapper(ComentMapper.class);
            return comentMapper.findById(comentId);
        }
    }

    public List<Coment> findByMemberId(Long postId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ComentMapper comentMapper = session.getMapper(ComentMapper.class);
            return comentMapper.findByPostId(postId);
        }
    }
}
