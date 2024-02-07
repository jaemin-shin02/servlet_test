package main.java.mybatis.mapper;

import main.java.domain.Coment;

import java.util.List;

public interface ComentMapper {
    void insertComent(Coment coment);
    void updateComent(Coment coment);
    void deleteComent(Long comentId);

    Coment findById(Long comentId);

    List<Coment> findByPostId(Long postId);
}
