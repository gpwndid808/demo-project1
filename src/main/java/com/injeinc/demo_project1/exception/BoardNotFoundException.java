package com.injeinc.demo_project1.exception;

// TODO [5단계] 커스텀 예외 클래스 만들기
//  - RuntimeException을 상속받아 커스텀 예외를 만듭니다.
//  - 체크 예외(Checked Exception) vs 언체크 예외(Unchecked Exception) 이해
//  💡 학습 포인트: RuntimeException은 트랜잭션을 롤백시킵니다.

// TODO [1단계] Lombok 적용하기 (선택사항)
//  - @Getter 어노테이션으로 getter 메서드 자동 생성
//  💡 실습: Lombok으로 코드 간소화

public class BoardNotFoundException extends RuntimeException {
    
    // TODO [5단계] 예외 메시지 생성자
    //  - 게시글 ID를 받아서 의미있는 에러 메시지 생성
    //  - super()로 부모 클래스의 생성자 호출
    //  💡 예시: "게시글을 찾을 수 없습니다: " + id
    public BoardNotFoundException(String id) {
        super("게시글을 찾을 수 없습니다: " + id);
    }
    
    // TODO [5단계] 추가 생성자 (선택사항)
    //  - 원인(cause)을 함께 전달하는 생성자
    //  💡 실습: public BoardNotFoundException(String message, Throwable cause)
    
    // TODO [5단계] 다른 커스텀 예외도 만들어보기
    //  - InvalidBoardDataException: 잘못된 입력 데이터
    //  - UnauthorizedAccessException: 권한 없음
    //  💡 고민: 예외 클래스를 언제 새로 만들고 언제 기존 것을 사용할까?
}
