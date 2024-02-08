package main.java.service;

import main.java.domain.Coment;
import main.java.mybatis.mapper.ComentMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ComentService {
    private final SqlSession sqlSession;
    private final ComentMapper comentMapper;

    public ComentService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.comentMapper = sqlSession.getMapper(ComentMapper.class);
    }

    public void addComent(Coment coment) {
        comentMapper.insertComent(coment);

        sqlSession.commit();
    }

    public void deleteComent(Long comentId) {
        comentMapper.deleteComent(comentId);
        sqlSession.commit();
    }

    public Coment findById(Long comentId) {
        return comentMapper.findById(comentId);
    }

    public List<Coment> findAll() {
        return comentMapper.findAll();
    }

    public List<Coment> findByPostId(Long postId) {
        return comentMapper.findByPostId(postId);
    }
}
