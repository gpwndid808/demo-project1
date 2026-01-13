package com.injeinc.demo_project1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// TODO [8단계] @MappedSuperclass 이해하기
//  - 이 클래스는 실제 테이블로 생성되지 않습니다.
//  - 자식 엔티티들이 이 클래스의 필드를 상속받아 사용합니다.
//  - 공통 필드(생성일시, 수정일시)를 여러 엔티티에서 재사용할 수 있습니다.
//  💡 학습 포인트: 상속을 통한 코드 재사용

// TODO [8단계] @EntityListeners 이해하기
//  - JPA 이벤트를 감지하는 리스너를 등록합니다.
//  - AuditingEntityListener: JPA Auditing 기능 활성화
//  - 엔티티가 생성/수정될 때 자동으로 시간 정보를 설정합니다.
//  💡 학습 포인트: JPA 콜백 메커니즘

// TODO [8단계] JPA Auditing 활성화하기
//  - DemoProject1Application.java에 @EnableJpaAuditing 추가 필요!
//  - 이 어노테이션이 없으면 @CreatedDate, @LastModifiedDate가 동작하지 않습니다.
//  💡 실습: 메인 클래스에 @EnableJpaAuditing 추가

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    
    // TODO [8단계] @CreatedDate 이해하기
    //  - 엔티티가 처음 저장될 때 자동으로 현재 시간 설정
    //  - updatable = false: 이후 수정되지 않도록 보호
    //  💡 학습 포인트: 생성 시간은 변경되면 안 됩니다!
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;
    
    // TODO [8단계] @LastModifiedDate 이해하기
    //  - 엔티티가 수정될 때마다 자동으로 현재 시간으로 갱신
    //  - 더티 체킹으로 필드가 변경되면 자동 업데이트
    //  💡 학습 포인트: 수정 시간은 자동으로 관리됩니다!
    @LastModifiedDate
    private LocalDateTime modifiedDate;
    
    // TODO [8단계] LocalDateTime vs Date
    //  - LocalDateTime: Java 8 이상에서 권장 (불변, 스레드 세이프)
    //  - Date/Timestamp: 레거시 방식 (가변, 스레드 세이프 X)
    //  💡 학습 포인트: 최신 Java는 LocalDateTime 사용을 권장합니다.
    
    // TODO [8단계] Getter 추가하기
    //  - 생성/수정 시간을 조회할 수 있도록 getter 제공
    //  - setter는 제공하지 않음 (자동으로 설정되므로)
    //  💡 실습: Lombok의 @Getter 사용 가능
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
    
    // TODO [8단계] 작성자/수정자 정보도 자동화하기 (심화)
    //  - @CreatedBy: 생성자 자동 설정
    //  - @LastModifiedBy: 수정자 자동 설정
    //  - AuditorAware 인터페이스 구현 필요
    //  💡 실습: 아래 주석을 해제하고 AuditorAware 구현
    /*
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    
    @LastModifiedBy
    private String modifiedBy;
    */
    
    // TODO [8단계] AuditorAware 구현하기 (심화)
    //  - 현재 로그인한 사용자 정보를 반환하는 인터페이스
    //  - Spring Security와 연동하여 자동으로 사용자 정보 입력
    //  💡 예시: SecurityContextHolder.getContext().getAuthentication().getName()
    //  💡 학습 포인트: Spring Security 학습 후 적용
    
    // TODO [8단계] 소프트 삭제(Soft Delete) 추가하기 (선택사항)
    //  - 물리적 삭제 대신 삭제 여부만 표시
    //  - private boolean deleted = false;
    //  - private LocalDateTime deletedDate;
    //  💡 실습: @SQLDelete, @Where 어노테이션 학습
}
