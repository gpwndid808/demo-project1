package com.injeinc.demo_project1.service;

import java.util.List;

import com.injeinc.demo_project1.dto.CommentRequestDto;
import com.injeinc.demo_project1.dto.CommentResponseDto;
import com.injeinc.demo_project1.entity.Comment;

public interface CommentService {
	
	public Comment createComment(String id, CommentRequestDto request);
	
	public List<Comment> getCommentsByBoardId(String id);
	
	public List<Comment> getCommentsByWriter(String writer);
	
	public void deleteComment(Long commentNum);
}
