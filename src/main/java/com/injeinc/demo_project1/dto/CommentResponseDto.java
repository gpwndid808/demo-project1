package com.injeinc.demo_project1.dto;

import java.time.LocalDateTime;

import com.injeinc.demo_project1.entity.Board;
import com.injeinc.demo_project1.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
	
	private Long boardId;
    private String comment;
    private String writer;
    private LocalDateTime rgstrDt;
    private LocalDateTime mdfcnDt;
    
    public CommentResponseDto(Comment comment) {
        this.boardId = comment.getBoardId();
        this.comment = comment.getComment();
        this.writer = comment.getWriter();
    }
    
    public static CommentResponseDto from(Comment comment) {
    	return CommentResponseDto.builder()
    		.boardId(comment.getBoardId())
    		.comment(comment.getComment())
    		.writer(comment.getWriter())
    		.rgstrDt(comment.getCreatedDate())
    		.mdfcnDt(comment.getModifiedDate())
    		.build();
    }
}
