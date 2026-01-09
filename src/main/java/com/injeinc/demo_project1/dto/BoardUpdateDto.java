package com.injeinc.demo_project1.dto;

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
    private String boardTitle;
    private String boardCn;
    
    // Getter & Setter
    // TODO [1단계] Lombok으로 대체 가능
}
