package com.bootstudy.demo.persistence;

import java.util.Collection;
import java.util.List;

import com.bootstudy.demo.domain.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * BoardRepository
 */
public interface BoardRepository extends CrudRepository<Board, Long> {

    @Query("SELECT b from Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
    public List<Board> findByTitle(String title);

    // @Query("SELECT b from Board b where b.content like %:content% and b.bno > 0 order by b.bno desc")
    // public List<Board> findByContent(@Param("content") String content);

    public Collection<Board> findByWriter(String writer);

    public Collection<Board> findByWriterContaining(String writer);

    public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);

    public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

    //페이징 처리
    public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);
    //페이징 처리 Pag<T> 타입
    public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);

    @Query("select b.bno, b.title, b.writer, b.regdate " + " from Board b where b.title like %?1% and b.bno > 0 order by b.bno desc")
    public List<Object[]> findByTitle2(String title);

    @Query(value="select bno, title, writer from tbl_boards where title like concat('%',?1, '%') and bno > 0 order by bno desc", nativeQuery=true )
    public List<Object[]> findByTitle3(String title);

    @Query("select b from Board b where b.bno > 0 order by b.bno desc")
    public List<Board> findBypage(Pageable pageable);
}