package com.injeinc.demo_project1.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.injeinc.demo_project1.entity.Board;
import com.injeinc.demo_project1.repository.BoardRepository;

//import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DemoServiceImpl implements DemoService {

    private final BoardRepository boardRepository;
    
    public DemoServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    
    @Override
    public List<Board> retvLstBoard() {
        return boardRepository.findAll();
    }
}
