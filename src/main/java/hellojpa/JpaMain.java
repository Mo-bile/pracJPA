package hellojpa;

import RoleType.RoleType;
import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // db커넥션 받안다고 생각해

        EntityTransaction tx = em.getTransaction(); // 모든 변경은 트랜잭션 안에서 해야함
        tx.begin();
        try {
            Member member = new Member();
            member.setId(1L);
            member.setUsername("B");
            member.setRoleType(RoleType.ADMIN);

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}