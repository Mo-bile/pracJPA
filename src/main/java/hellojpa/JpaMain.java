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

            Member member = new Member();
            // 실무에서는 생성자로 빌더패턴으로 주로함
            // setter 자신은 잘 안씀
            member.setUsername("member3");

            em.persist(member);

            Team team = new Team();
            team.setName("teamC");
            // 일대다에서 뭔가 어색함
            //member table의 내용업뎃필요
            team.getMembers().add(member);
            em.persist(team);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}