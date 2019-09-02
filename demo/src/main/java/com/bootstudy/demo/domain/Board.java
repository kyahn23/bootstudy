package com.bootstudy.demo.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Board
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "tbl_boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bno;

    private String title;
    private String content;
    private String writer;

    @CreationTimestamp
    private Timestamp regdate;
    @UpdateTimestamp
    private Timestamp updatedate;
}