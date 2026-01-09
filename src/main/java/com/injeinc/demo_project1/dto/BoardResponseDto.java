package com.injeinc.demo_project1.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import com.injeinc.demo_project1.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// TODO [3단계] Response DTO 이해하기
//  - 클라이언트에게 응답할 데이터를 담는 객체입니다.
//  - Entity를 직접 반환하지 않고 DTO로 변환하여 반환합니다.
//  💡 학습 포인트: 민감한 정보 노출 방지, API 스펙 고정

// TODO [1단계] Lombok 활용하기 
//  - @Getter, @NoArgsConstructor, @AllArgsConstructor 추가
//  - @Builder 추가하여 빌더 패턴 사용
//  💡 실습: Lombok 어노테이션 추가 후 아래 코드 간소화

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    
    // TODO [3단계] 응답할 필드 정의
    //  - 클라이언트에게 보여줄 필드만 선택합니다.
    //  - Entity의 모든 필드를 노출할 필요는 없습니다.
    private Long boardId;
    private String boardTitle;
    private String boardCn;
    private String rgstrUsrId;
    private LocalDateTime rgstrDt;
    private LocalDateTime mdfcnDt;
    
    // TODO [3단계] Entity -> DTO 변환 생성자
    //  - Entity를 받아서 DTO로 변환하는 생성자입니다.
    //  - Service에서 사용합니다.
    //  💡 예시:
      public BoardResponseDto(Board board) {
          this.boardId = board.getBoardId();
          this.boardTitle = board.getBoardTitle();
          this.rgstrUsrId = board.getRgstrUsrId();
      }
    
    // TODO [3단계] 정적 팩토리 메서드 패턴 (권장)
    //  - 생성자 대신 정적 메서드를 사용하여 의미를 명확히 합니다.
    //  💡 예시:
      public static BoardResponseDto from(Board board) {
          return BoardResponseDto.builder()
              .boardId(board.getBoardId())
              .boardTitle(board.getBoardTitle())
              .boardCn(board.getBoardCn())
              .rgstrUsrId(board.getRgstrUsrId())
              .rgstrDt(board.getRgstrDt())
              .mdfcnDt(board.getMdfcnDt())
              .build();
      }
    
    // Getter
    // TODO [1단계] Lombok @Getter로 대체 가능
}
