package com.example.shop.entity;

import com.example.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Item {
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //상품코드

    @Column(nullable = false,length=50)
    private String itemNm; //상품명

    @Column(name="price", nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;//상품 판매 상태

    private LocalDateTime regTime; //등록 시간

    private LocalDateTime updateTime;//수정 시간
}


/*

@Entity   -  클래스를 엔티티로선언
@Table - 엔티티와 매핑할 테이블을 지정
@Id - 테이블의 기본키에 사용할 속성을 지정
@GeneratedValue - 키값을 생성하는 전략 명시
                     GenerationType.AUTO : 자동증가
                     GenerationType.IDENTITY : 기본키 생성
                     GenerationType.SEQUENCE : 데이터베이스 시퀸스 오브젝트를 이용한 기본키 생성
                     GenerationType.TABLE  :  키 생성용 테이블 사용

@Column - 필드와 컬럼 매핑
           name : 필드와 매핑할 컬럼의 이름 설정
           unique(DDL) : 유니크 제약 조건 설정
           insertable : insert 가능 여부
           updateable : update 가능 여부
           nullable : null 값의 허용 여부 설정, false 설정 시 DDL 생성시에 not null 제약조건 추가
           columnDefinition : 데이터베이스 컬럼 정보 직접 기술
           precision, scale : BigDecimal 타입에서 사용 , precision은 소수점을 포함한 전체 자리수이고, scale은 소수점 자리수

@Lob - BLOB, CLOB 타입 매핑 , 대 용량 데이터저장
       BLOB :  이진 데이터를 저장 , 이미지나 파일
       CLOB : 문자열이나 텍스트 데이터를 저장
@CreationTimestamp  - insert 시 시간 자동 저장
@UpdateTimestamp  - update 시 시간 자동 저장
@Enumerated  - enum 타입 매핑
@Transient - 해당 필드 데이터베이스 매핑 무시
@temporal - 날짜 타입 매핑
@CreateDate - 엔티티가 생성되어 저장될때 시간 자동 저장
@LastModifiedDate - 조회한 엔티티의 값을 변경할 때 시간 자동 저장

 */




