package com.injeinc.demo_project1.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.injeinc.demo_project1.dto.BoardRequestDto;
import com.injeinc.demo_project1.dto.BoardResponseDto;
import com.injeinc.demo_project1.dto.BoardUpdateDto;
import com.injeinc.demo_project1.entity.Board;
import com.injeinc.demo_project1.exception.BoardNotFoundException;
import com.injeinc.demo_project1.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;

// TODO [9단계] @SpringBootTest 이해하기
//  - 전체 애플리케이션 컨텍스트를 로드하는 통합 테스트
//  - @DataJpaTest보다 무겁지만 실제 환경과 유사
//  - Service, Repository 등 모든 빈이 주입됩니다.
//  💡 학습 포인트: 단위 테스트 vs 통합 테스트

// TODO [9단계] @Transactional 이해하기 (테스트용)
//  - 각 테스트 메서드 실행 후 자동 롤백
//  - 테스트 간 데이터 격리 보장
//  - DB에 실제로 데이터가 남지 않음
//  💡 학습 포인트: 테스트 격리의 중요성

@SpringBootTest
@Transactional
public class BoardServiceTest {
    
    // TODO [9단계] 테스트 대상 주입
    //  - Service와 Repository 모두 필요
    @Autowired
    private DemoService boardService;
    
    @Autowired
    private BoardRepository boardRepository;
    
    @Test
    @DisplayName("게시글 작성 테스트")
    void createBoard() {
        // TODO [9단계] Service 테스트 작성하기
        //  - given: DTO 생성
        //  - when: Service의 createBoard() 호출
        //  - then: 결과 검증 및 DB 저장 확인
        //  💡 실습: 아래 주석을 해제하고 완성하세요
        
        // given
        BoardRequestDto dto = new BoardRequestDto(
            "테스트 제목", 
            "테스트 내용", 
            "testUser",
    	 	"testUser"
        );
        
        // when
        Board result = boardService.createBoard(dto);
        
        // then
        assertThat(result).isNotNull();
        assertThat(result.getBoardTitle()).isEqualTo("테스트 제목");
        
        // DB에 실제로 저장되었는지 확인
        Board found = boardRepository.findById(result.getBoardId().toString()).orElseThrow();
        assertThat(found.getBoardTitle()).isEqualTo("테스트 제목");
        
    }
    
    @Test
    @DisplayName("게시글 단건 조회 테스트")
    void findById() {
        // TODO [9단계] 조회 테스트 작성하기
        //  - given: 게시글 미리 저장
        //  - when: Service의 findById() 호출
        //  - then: 조회된 데이터 검증
        //  💡 실습: Repository로 직접 저장 후 Service로 조회
    	
    	//given
    	BoardRequestDto dto = new BoardRequestDto(
                "테스트 제목", 
                "테스트 내용", 
                "testUser",
        	 	"testUser"
        );
        Board result = boardService.createBoard(dto);
        
        //when 
        Board board = boardService.findById(result.getBoardId().toString());
        
        //then
        assertThat(board).isNotNull();
        assertThat(board.getBoardId()).isEqualTo(result.getBoardId());
        
    }
    
    @Test
    @DisplayName("존재하지 않는 게시글 조회 시 예외 발생")
    void findByIdNotFound() {
        // TODO [9단계] 예외 테스트 작성하기
        //  - given: 존재하지 않는 ID
        //  - when & then: 예외 발생 확인
        //  💡 실습: assertThatThrownBy() 사용
        //  💡 예시: 아래 주석을 해제하고 완성하세요
        
        // given
        String notExistId = "999";
        
        // when & then
        assertThatThrownBy(() -> boardService.findById(notExistId))
            .isInstanceOf(BoardNotFoundException.class)
            .hasMessageContaining("게시글을 찾을 수 없습니다");
        
    }
    
    @Test
    @DisplayName("게시글 수정 테스트 (더티 체킹)")
    void updateBoard() {
        // TODO [9단계] 수정 테스트 작성하기
        //  - given: 게시글 저장
        //  - when: updateBoard() 호출
        //  - then: 수정된 내용 확인 (더티 체킹 동작 확인)
        //  💡 실습: 아래 주석을 해제하고 완성하세요
        
        // given
        Board board = boardRepository.save(Board.builder()
            .boardTitle("원래 제목")
            .boardCn("원래 내용")
            .build());
        String boardId = board.getBoardId().toString();
        
        BoardUpdateDto updateDto = new BoardUpdateDto("수정된 제목", "수정된 내용", "kiki");
        
        // when
        Board updated = boardService.updateBoard(boardId, updateDto);
        
        // then
        assertThat(updated.getBoardTitle()).isEqualTo("수정된 제목");
        assertThat(updated.getBoardCn()).isEqualTo("수정된 내용");
        
        // 트랜잭션 커밋 후 다시 조회하여 확인
        Board found = boardRepository.findById(boardId).orElseThrow();
        assertThat(found.getBoardTitle()).isEqualTo("수정된 제목");
    }
    
    @Test
    @DisplayName("게시글 삭제 테스트")
    void deleteBoard() {
        // TODO [9단계] 삭제 테스트 작성하기
        //  - given: 게시글 저장
        //  - when: deleteBoard() 호출
        //  - then: findById()로 조회 시 예외 발생 확인
        //  💡 실습: 삭제 후 조회했을 때 예외가 발생하는지 검증
    	
    	//given
    	Board board = boardRepository.save(Board.builder()
                .boardTitle("원래 제목")
                .boardCn("원래 내용")
                .build());
        String boardId = board.getBoardId().toString();
        
        //when
        boardRepository.deleteById(boardId);
        
        //then
        assertThatThrownBy(() -> boardService.findById(boardId))
        .isInstanceOf(BoardNotFoundException.class)
        .hasMessageContaining("게시글을 찾을 수 없습니다");
    }
    
    @Test
    @DisplayName("존재하지 않는 게시글 삭제 시 예외 발생")
    void deleteNotExistBoard() {
        // TODO [9단계] 예외 케이스 테스트
        //  - 존재하지 않는 ID로 삭제 시도
        //  - BoardNotFoundException 발생 확인
        //  💡 학습 포인트: 예외 상황도 테스트해야 합니다!
    	// given
        String notExistId = "999";
        
        // when & then
        assertThatThrownBy(() -> boardService.findById(notExistId))
            .isInstanceOf(BoardNotFoundException.class)
            .hasMessageContaining("게시글을 찾을 수 없습니다");
    }
    
    // TODO [9단계] 페이징 테스트 작성하기
    //  - findAllWithPaging() 메서드 테스트
    //  - Page 객체의 정보 검증
    //  💡 실습: 여러 게시글 저장 후 페이징 결과 확인
//    @Test
//    @DisplayName("페이징 테스트")
//    void pagingTest() {
//    	
//    	//given
//    	for(int i = 1; i <= 25; i++) {
//    		boardRepository.save(Board.builder()
//    								.boardTitle("title-" + i)
//    								.boardCn("boardCn-" + i)
//    								.rgstrUsrId("rgstrUsrId" + i)
//    								.mdfcnUsrId("mdfcnUsrId" + i)
//    								.build()
//								);
//    	}
//    	boardRepository.flush();
//    	
//    	//when
//    	 Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
//         Page<BoardResponseDto> page = boardService.findAllWithPaging(pageable);
//    	}
//    	
//    	//assertThat(page.).isEqualTo(2);
//    }
    
    // TODO [9단계] 검색 테스트 작성하기
    //  - searchByTitle() 메서드 테스트
    //  - 검색 결과 개수 및 내용 검증
    @Test
    @DisplayName("검색테스트")
    void searchTitle() {
    	//given
    	Board board = boardRepository.save(Board.builder()
                .boardTitle("원래 제목")
                .boardCn("원래 내용")
                .build());
        String boardId = board.getBoardId().toString();
        
        //when
        boardRepository.save(board);
        List<Board> result = boardRepository.findByBoardTitleContaining("원래 제목");
        
        //then
        assertThat(result).hasSize(1);
    }
    
    // TODO [9단계] Mock을 사용한 단위 테스트 (심화)
    //  - @Mock, @InjectMocks 사용
    //  - Repository를 Mocking하여 Service만 테스트
    //  - Mockito.when().thenReturn() 사용
    //  💡 학습 포인트: 의존성을 격리한 순수 단위 테스트
    
    // TODO [9단계] 테스트 커버리지 확인하기
    //  - IntelliJ: Run with Coverage
    //  - Gradle: ./gradlew test jacocoTestReport
    //  - 목표: 80% 이상 커버리지
    //  💡 실습: 테스트하지 않은 코드 찾아서 테스트 추가
}
