package com.injeinc.demo_project1.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.injeinc.demo_project1.entity.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
	
	private Long boardId;
    
    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 1000, message = "내용은 1000자 이내여야 합니다.")
    private String comment;
    
    private String writer;
    
}
