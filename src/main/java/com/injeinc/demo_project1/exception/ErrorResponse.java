package com.injeinc.demo_project1.exception;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// TODO [5단계] 에러 응답 DTO 만들기
//  - 클라이언트에게 일관된 형식의 에러 정보를 전달합니다.
//  - 에러 코드, 메시지 등을 포함합니다.
//  💡 학습 포인트: API 응답 형식의 일관성이 중요합니다.

// TODO [1단계] Lombok 적용하기
//  - @Getter: getter 메서드 자동 생성
//  - @AllArgsConstructor: 모든 필드를 받는 생성자 생성
//  - @Builder: 빌더 패턴 적용
//  💡 실습: Lombok 어노테이션 추가 후 수동 코드 제거

//@AllArgsConstructor
//@Getter
//@Setter
public class ErrorResponse {
    
    // TODO [5단계] 에러 응답 필드 정의
    //  - code: 에러 코드 (예: "NOT_FOUND", "VALIDATION_ERROR")
    //  - message: 사용자에게 보여줄 에러 메시지
    //  💡 추가 고려사항: timestamp, path, details 등
    private String code;
    private String message;
    
    // TODO [5단계] 기본 생성자
    //  - JSON 직렬화/역직렬화를 위해 필요
    public ErrorResponse() {}
    
    // TODO [5단계] 전체 필드 생성자
    //  - 에러 응답 생성 시 사용
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
    
    // TODO [5단계] 타임스탬프 추가하기 (선택사항)
    //  - 에러 발생 시간을 기록
    //  - private LocalDateTime timestamp;
    //  - 생성자에서 LocalDateTime.now()로 초기화
    private LocalDateTime timestamp;
    
    // TODO [5단계] 상세 정보 추가하기 (선택사항)
    //  - private List<String> details; // 여러 개의 에러 메시지
    //  - private String path; // 에러가 발생한 API 경로
    //  💡 실습: Validation 에러 시 여러 필드의 에러를 한 번에 반환
//    private List<String> details;
//    private String path;
    
    // Getter
    // TODO [1단계] Lombok @Getter로 대체 가능
    public String getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    
    // Setter (필요한 경우)
    public void setCode(String code) {
        this.code = code;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
