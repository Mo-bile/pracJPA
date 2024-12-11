package jpql;

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
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            Member singleResult = em.createQuery("select m from Member m", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("singleResult = " + singleResult.getUsername());
//            List<Member> resultList = query.getResultList();
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1.getUsername());
//            }


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