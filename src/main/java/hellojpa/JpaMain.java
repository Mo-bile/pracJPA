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

            Member member1 = new Member();
            member1.setUsername("hello1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("hello2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.getReference(Member.class, member2.getId());

            System.out.println("emf.getPersistenceUnitUtil().isLoaded() = "
                    + emf.getPersistenceUnitUtil().isLoaded(m2));
            m2.getUsername();
            System.out.println("emf.getPersistenceUnitUtil().isLoaded() = "
                    + emf.getPersistenceUnitUtil().isLoaded(m2));


            // find 끼리하면 true 가 나옴
            // getReference 면은 false 가 나옴
            System.out.println("m1 == m2 :"+ (m1.getClass() == m2.getClass()));
            System.out.println("m1  :"+ (m1 instanceof Member));
            System.out.println("m1  :"+ (m2 instanceof Member));

            //
            System.out.println("m1 = " + m1.getClass());
            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference = " + reference.getClass());
            // 일반적인 방식
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getUsername());

            //
//            Member findMember = em.getReference(Member.class, member2.getId());
//            //아래와 같은 실제 사용 때 쿼리문을 실행 함
//            System.out.println("findMember = " + findMember);
//            System.out.println("findMember.name = " + findMember.getUsername());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}