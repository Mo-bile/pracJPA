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

            for (int i = 0 ; i < 100 ; i++){
                Member member = new Member();
                member.setUsername("member" + i);
                member.setAge(i);
                em.persist(member);
            }
            em.flush();
            em.clear();

//            String query =
//                    "select " +
//                            "case when m.age <= 10 then '학생요금'" +
//                                "when m.age >= 60 then '경로요금'" +
//                                "     else '일반요금'" +
//                                "end " +
//                    "from Member m ";

            String query = "select coalesce(m.username, '이름없는 회원') from Member m";

            List<String> resultList = em.createQuery(query, String.class)
                    .getResultList();
            for (String s : resultList) {
                System.out.println(s);
            }

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