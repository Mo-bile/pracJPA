package jpabook.jpashop.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;
    @Column(name = "MEMBER_ID")
    private Long memberId; // -> 객체지향 스럽지않음
//    private Member member;


    private LocalDateTime orderDate;
    //ORDER_DATE, order_date //springboot 는 카멜이 스네이크로 바뀜


    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}