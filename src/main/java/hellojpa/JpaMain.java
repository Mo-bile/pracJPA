package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // db커넥션 받안다고 생각해

        EntityTransaction tx = em.getTransaction(); // 모든 변경은 트랜잭션 안에서 해야함
        tx.begin();
        try {
            //비영속 상태!!!!!!!!
            Member member = new Member();
            member.setId(100L);
            member.setName("Hello");

            // 영속 상태로!!! // 전,후 사이에 쿼리가 안나옴
            System.out.println("=== BEFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 커넥션 물고 동작하기 때문에 꼭 닫아 주어야함
        }
        emf.close(); // 팩토리 까지 닫음 (스프링이면 was내려갈 때 해주어야함)
    }
}
