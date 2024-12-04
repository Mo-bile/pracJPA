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

            Member member = new Member();
            member.setUsername("user1");
            member.setCreatedBy("kim");
            member.setCratedDate(LocalDateTime.now());

            em.persist(member);

//            Movie movie = new Movie();
//            movie.setDirector("A director");
//            movie.setActor("b Actor");
//            movie.setName("바람과 함께 사라지다");
//            movie.setPrice(10000);
//
//            em.persist(movie);
//
            em.flush();
            em.clear();


//            // join 볼수 있음
//            /*    select
//        m1_0.id,
//        m1_1.name,
//        m1_1.price,
//        m1_0.actor,
//        m1_0.director
//    from
//        Movie m1_0
//    join
//        Item m1_1
//            on m1_0.id=m1_1.id
//    where
//        m1_0.id=?*/
//            Movie findMoive = em.find(Movie.class, movie.getId());
//            System.out.println("findMoive = " + findMoive);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}