package jpabook.jpashop.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    //첫 설계는 단방향 추천!!!!!
    @ManyToOne //고객은 하나
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order") // 외래키로 잡음
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;
    //ORDER_DATE, order_date //springboot 는 카멜이 스네이크로 바뀜

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

}