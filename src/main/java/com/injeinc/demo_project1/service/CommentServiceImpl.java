package com.injeinc.demo_project1.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.injeinc.demo_project1.dto.CommentRequestDto;
import com.injeinc.demo_project1.dto.CommentResponseDto;
import com.injeinc.demo_project1.entity.Board;
import com.injeinc.demo_project1.entity.Comment;
import com.injeinc.demo_project1.exception.BoardNotFoundException;
import com.injeinc.demo_project1.repository.BoardRepository;
import com.injeinc.demo_project1.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;
	
	@Transactional
	@Override
	public Comment createComment(String id, CommentRequestDto request) {
		
		Board board = boardRepository.findById(id)
					.orElseThrow(() -> new BoardNotFoundException(id)); 
		Comment comment = new Comment(request.getBoardId(), request.getComment(), request.getWriter(), board);
		commentRepository.save(comment);
		  
		return comment;
	}
	
	@Override
	public List<Comment> getCommentsByBoardId(String id) {
		
//		List<Comment> comments = commentRepository.findByBoardBoardId(id);
		List<Comment> comments = commentRepository.findByBoardBoardIdOrderByCreatedDateDesc(id);
		return comments;
	}

	@Override
	public List<Comment> getCommentsByWriter(String writer) {
		// TODO Auto-generated method stub
		
		List<Comment> comments = commentRepository.findByWriter(writer);
		return comments;
	}

	@Transactional
	@Override
	public void deleteComment(Long commentNum) {
		// TODO Auto-generated method stub
		commentRepository.deleteById(commentNum);
	}
}
