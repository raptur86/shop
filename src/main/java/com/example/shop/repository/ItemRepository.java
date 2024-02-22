package com.example.shop.repository;

import com.example.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> , QuerydslPredicateExecutor<Item> {
    List<Item> findByItemNm(String itemNm);

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    List<Item> findByPriceLessThan(Integer price);

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);


    @Query("select i from Item i where i.itemDetail like " +
            "%:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    @Query(value="select * from item i where i.item_detail like " +
            "%:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

}

// jpa를 통해 간단한 네이밍 룰을 이용하여  메서드를 작성하면 원하는 쿼리를 실행 할 수 있다.
// find + (엔티티 이름) + By + 변수이름


/* 2.6  spring Data JPA @ query

    @query 애노테이션을 이용하면 sql과 유사한 JPQL이라는 객체지향 쿼리 언어를 통해 복잡한 쿼리도 처리가 가능하다


    @Query("select i from Item i where i.itemDetail like " +
            "%:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

     - 상품 상세 설명에 포함 하고 있는 데이터를 조회 하고 정렬순서는 가격이 높은 순으로 조회 한다.
     - @Query 어노테이션 안에 JPQL로 작성한 쿼리문을 넣어준다.  from 뒤에는 엔티티 클래스로 작성한 item을 지정해주었고,
       Item으로부터 데이터를 select 하겠다는 것을 의미 한다.
     - 파라미터에 @Param 어노테이션을 이용하여 파라미터로 넘어 온 값을 JPQL에 들어갈 변수로 지정해 줄 수 있다.
        현재는 itemDetail 변수를 "like % %" 사이에 ":itemDetail로 값이 들어가도록 작성한다.


2.7  spring DATA JPA Querydsl
   JPQL의 단점은  naticesql의 단점을 가진다.
   문자열로 쿼리를 입력하기 때문에 잘못입력하여 도 컴파일 시점에 에러를 발견 할 수 없다.
   보안 하는게 Querydsl이다.

querydsl 장점
 - 고정된 SQL문이 아닌 조건에 맞게 동적을 ㅗ쿼리를 생성 하 수 있다.
 - 비슷한 쿼리를 재사용할 수 있으면 제약 조건 조립 및 가독성을 향상 시킬수 있다.
 - 문자열이 아닌 자바 소스코드로 작성하기 때문에 컴파일 시점에 오류를 발견 할 수 있다.
 - IDE으 ㅣ도움을 받아서 자동완성 기능을 이용할수 있기 에 생산성 향상


list<T> fetch()   조회결과 리스트 반환
T fetchone    조회 대상이 1건인 경우 제네릭으로 지정한 타입 반환
T fetchfirst()    조회 대상중 1건만 반환
Long fetchcount() 조회 대상 개수 반환
QueryResuilt<T>  fetchResults()   조회한 리스트와 전체 개수를 포함한 QueryResults 반환


 */