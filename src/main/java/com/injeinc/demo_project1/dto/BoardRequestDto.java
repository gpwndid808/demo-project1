package com.injeinc.demo_project1.dto;

import java.time.LocalDateTime;

import com.injeinc.demo_project1.entity.Board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO [3단계] DTO(Data Transfer Object) 이해하기
//  - Controller와 Service 간 데이터 전송을 위한 객체입니다.
//  - Entity를 직접 노출하지 않고 DTO를 사용합니다.
//  💡 학습 포인트: Entity와 DTO를 분리하는 이유
//    1) API 스펙 변경 시 Entity 영향 최소화
//    2) 필요한 데이터만 전송 (불필요한 필드 숨김)
//    3) Validation 로직 분리

// TODO [1단계] Lombok 활용하기
//  - @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor 추가
//  - 또는 @Data 어노테이션으로 한 번에 추가
//  💡 실습: Lombok 어노테이션을 추가하고 아래 getter/setter 제거

// TODO [5단계] Bean Validation 적용하기
//  - @NotBlank(message = "제목은 필수입니다.")
//  - @Size(min = 1, max = 100, message = "제목은 1~100자 이내여야 합니다.")
//  💡 import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
    
    // TODO [3단계] 게시글 작성 시 필요한 필드 정의
    //  - 제목(title)과 내용(content)만 받습니다.
    //  - ID는 자동 생성되므로 받지 않습니다.
    //  💡 고민: 작성자 정보는 어떻게 받을까요?
	private Long boardId;
	
    @NotBlank(message = "제목은 필수입니다.")
    @Size(min = 1, max = 100, message = "제목은 1~100자 이내여야 합니다.")
    private String boardTitle;
    
    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 1000, message = "내용은 1000자 이내여야 합니다.")
    private String boardCn;
    
    private String rgstrUsrId;
    private String mdfcnUsrId;
    
    // TODO [3단계] toEntity() 메서드 추가하기
    //  - DTO를 Entity로 변환하는 메서드입니다.
    //  - Service에서 사용할 예정입니다.
    //  💡 예시:
    public Board toEntity() {
    	return Board.builder()
			.boardTitle(this.boardTitle)
			.boardCn(this.boardCn)
			.rgstrUsrId(this.rgstrUsrId)
			.mdfcnUsrId(this.mdfcnUsrId)
			.build();
    }
    
    // TODO [1단계] Lombok으로 대체 가능
    
    // Getter & Setter
    // TODO [1단계] Lombok @Getter, @Setter로 대체 가능
}
