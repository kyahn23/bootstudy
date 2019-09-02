package com.bootstudy.demo.controller;

import java.util.Arrays;
import java.util.List;

import com.bootstudy.demo.domain.Board;
import com.bootstudy.demo.persistence.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 */
@RestController
public class TestController {

    @Autowired
    BoardRepository repo;

    @GetMapping("/test")
    public String testmethod(){
        for (int i = 1; i <= 200; i++) {
            Board board = new Board();
            board.setTitle("제목.."+i);
            board.setContent("내용.."+i+"..채우기");
            board.setWriter("user0"+(i%10));
            repo.save(board);
        }

        return "테스트";
    }
    
    @GetMapping("/paging")
    public String testpaging(){

        Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");
        Page<Board> result = repo.findByBnoGreaterThan(0L, paging);
        System.out.println("Page Size : " + result.getSize());
        System.out.println("Total Pages : " + result.getTotalPages());
        System.out.println("Total Count : " + result.getTotalElements());
        System.out.println("Next : " + result.nextPageable());

        List<Board> list = result.getContent();
        list.forEach(board -> System.out.println(board));

        return "paging success";
    }

    @GetMapping("/querytest")
    public String querytest(){
        repo.findByTitle("17").forEach(board -> System.out.println(board));

        return "querytest";
    }

    @GetMapping("/querytest2")
    public String querytest2(){
        repo.findByTitle2("17")
        .forEach(arr -> System.out.println(Arrays.toString(arr)));
        return "querytest2";
    }
    
    @GetMapping("/paging2")
    public String testpaging2(){
        Pageable pageable = PageRequest.of(0, 10);

        repo.findBypage(pageable).forEach(board -> System.out.println(board));
        return "paging @query test";
    }
}