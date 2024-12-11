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

            List<MemberDTO> resultList =
                    em.createQuery("select new jpql.MemberDTO(m.username, m.age)" +
                            "from Member m", MemberDTO.class)
                            .getResultList();

            MemberDTO memberDTO = resultList.get(0);
            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());


            em.flush();
            em.clear();

//            List<Member> result = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();
//            Member findMember = result.get(0);
//            findMember.setAge(20);
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