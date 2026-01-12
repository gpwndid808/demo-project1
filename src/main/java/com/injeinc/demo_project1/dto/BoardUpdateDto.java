package com.injeinc.demo_project1.dto;

// TODO [5단계] Bean Validation import 추가하기
//  - 수정할 때도 입력값 검증이 필요합니다.
//  💡 실습: 아래 import 문의 주석을 해제하세요
 import jakarta.validation.constraints.NotBlank;
 import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO [4단계] Update DTO 이해하기
//  - 게시글 수정 시 사용하는 DTO입니다.
//  - 수정 가능한 필드만 포함합니다.
//  💡 학습 포인트: ID는 URL 경로로 받으므로 DTO에 포함하지 않습니다.

// TODO [1단계] Lombok 활용하기
//  - @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor 추가
//  💡 실습: Lombok으로 코드 간소화

// TODO [5단계] Bean Validation 적용하기
//  - @NotBlank 등을 추가하여 입력값 검증
//  - 수정할 때 빈 값으로 덮어쓰는 것을 방지
//  💡 실습: 빈 값이 들어오면 에러 발생하도록 설정

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateDto {
    
    // TODO [4단계] 수정 가능한 필드만 정의
    //  - 제목과 내용만 수정 가능합니다.
    //  - 작성자, 작성일시는 수정되지 않습니다.
    //  💡 고민: 수정자 정보(mdfcnUsrId)는 어떻게 처리할까요?
	
    // TODO [5단계] 제목 검증 추가하기
    //  - 수정 시에도 제목은 필수입니다.
    //  💡 실습: 아래 어노테이션의 주석을 해제하세요
     @NotBlank(message = "수정할 제목은 필수입니다.")
     @Size(min = 1, max = 100, message = "제목은 1~100자 이내여야 합니다.")
     private String boardTitle;
    

	// TODO [5단계] 내용 검증 추가하기
    //  - 수정 시에도 내용은 필수입니다.
    //  💡 실습: 적절한 Validation 어노테이션을 추가하세요
     @NotBlank(message = "수정할 내용은 필수입니다.")
     @Size(max = 1000, message = "내용은 1000자 이내여야 합니다.")
     private String boardCn;
     
    // Getter & Setter
    // TODO [1단계] Lombok으로 대체 가능
}
