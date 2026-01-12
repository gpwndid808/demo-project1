package com.injeinc.demo_project1.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// TODO [5단계] @RestControllerAdvice 이해하기
//  - 모든 @RestController에서 발생하는 예외를 한 곳에서 처리합니다.
//  - @ControllerAdvice + @ResponseBody의 조합
//  💡 학습 포인트: AOP(관점 지향 프로그래밍)의 실제 활용 사례

// TODO [5단계] 전역 예외 처리의 장점
//  - Controller마다 try-catch를 작성할 필요 없음
//  - 일관된 에러 응답 형식 유지
//  - 에러 처리 로직의 중앙 집중화
//  💡 학습 포인트: 관심사의 분리(Separation of Concerns)

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // TODO [5단계] @ExceptionHandler 이해하기
    //  - 특정 예외가 발생했을 때 실행될 메서드를 지정합니다.
    //  - 메서드 파라미터로 예외 객체를 받을 수 있습니다.
    //  💡 학습 포인트: 여러 예외를 배열로 지정 가능
    
    // TODO [5단계] BoardNotFoundException 처리하기
    //  - 게시글을 찾지 못했을 때 404 Not Found 응답
    //  - ErrorResponse 객체를 생성하여 반환
    //  💡 실습: 아래 메서드의 주석을 해제하고 완성하세요
    
    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBoardNotFound(BoardNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse("BOARD_NOT_FOUND", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    
    
    // TODO [5단계] Validation 예외 처리하기
    //  - @Valid 검증 실패 시 발생하는 MethodArgumentNotValidException 처리
    //  - BindingResult에서 에러 메시지 추출
    //  💡 실습: 아래 메서드의 주석을 해제하고 완성하세요
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        // 첫 번째 에러 메시지만 추출
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorResponse errorResponse = new ErrorResponse("VALIDATION_ERROR", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    
    // TODO [5단계] 모든 필드의 검증 에러 반환하기 (심화)
    //  - 여러 필드에서 에러가 발생한 경우 모두 반환
    //  💡 실습:
    //  List<String> errors = e.getBindingResult().getAllErrors()
    //      .stream()
    //      .map(error -> error.getDefaultMessage())
    //      .collect(Collectors.toList());
    
    // TODO [5단계] 일반적인 예외 처리하기
    //  - 예상하지 못한 모든 예외를 처리하는 fallback 핸들러
    //  - 500 Internal Server Error 응답
    //  💡 주의: 운영 환경에서는 상세한 에러 정보를 노출하지 않도록 주의!
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_ERROR", "서버 내부 오류가 발생했습니다.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
    
    
    // TODO [5단계] 추가 예외 처리 메서드 만들기
    //  - IllegalArgumentException: 잘못된 인자
    //  - DataIntegrityViolationException: DB 제약조건 위반
    //  - AccessDeniedException: 권한 없음 (추후 Security 적용 시)
    //  💡 실습: 필요한 예외 핸들러를 추가로 작성해보세요
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException e){
    	ErrorResponse errorResponse = new ErrorResponse("DATA_INTEGRITY_VIOLATION", "DB 무결성 제약조건이 위반되었습니다.");
    	return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
    
    // TODO [5단계] HTTP 상태 코드 이해하기
    //  - 200 OK: 성공
    //  - 201 Created: 리소스 생성 성공
    //  - 400 Bad Request: 잘못된 요청
    //  - 404 Not Found: 리소스를 찾을 수 없음
    //  - 500 Internal Server Error: 서버 내부 오류
    //  💡 학습 포인트: RESTful API의 HTTP 상태 코드 사용 규칙
    
    // TODO [5단계] 로깅 추가하기 (선택사항)
    //  - 에러 발생 시 로그를 남겨 디버깅에 활용
    //  - private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //  - log.error("Error occurred: ", e);
    //  💡 실습: 각 예외 핸들러에 로깅 추가
}
