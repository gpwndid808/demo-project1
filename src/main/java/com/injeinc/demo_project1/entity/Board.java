package com.injeinc.demo_project1.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String boardId;

    private String boardTitle;
    private String boardCn;
    private String rgstrUsrId;

    private Date rgstrDt;

    private String mdfcnUsrId;

    private Date mdfcnDt;

    protected Board() {}

    public String getBoardId() { return boardId; }
    public String getBoardTitle() { return boardTitle; }
    public String getBoardCn() { return boardCn; }
    public String getRgstrUsrId() { return rgstrUsrId; }
    public Date getRgstrDt() { return rgstrDt; }
    public String getMdfcnUsrId() { return mdfcnUsrId; }
    public Date getMdfcnDt() { return mdfcnDt; }
}