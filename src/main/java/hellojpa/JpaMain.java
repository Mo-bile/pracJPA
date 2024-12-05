package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // db커넥션 받안다고 생각해
        EntityTransaction tx = em.getTransaction(); // 모든 변경은 트랜잭션 안에서 해야함

        tx.begin();
        try {

            Address address = new Address("city", "street", "10000");

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            // 복사해서 넣어줌
            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipCode());

            Member member2 = new Member();
            member2.setUsername("member2");
//            member2.setHomeAddress(address);
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

            // update 가 member1, 2 에 모두 발생됨 -> 위 복사해서 넣어주는 방식으로
            member.getHomeAddress().setCity("newCity");


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}