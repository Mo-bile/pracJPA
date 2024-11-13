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

            // 1차 캐시에서 조회
//            //비영속 상태!!!!!!!!
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");
//
//            // 영속 상태로!!! // 전,후 사이에 쿼리가 안나옴
//            System.out.println("=== BEFORE ===");
//            // 저장시 1차 캐시에 저장됨
//            em.persist(member);
//            System.out.println("=== AFTER ===");
//
//            // select 쿼리가 안나감 -> 1차 캐시를 조회함
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

            // 두번째 멤버2는 1차캐시에서 가져옴
            // 조회만 하면 영속성 컨텍스트에 올림
//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
//
//            // 영속 엔티티의 동일성 보장
//            System.out.println("result = " + (findMember1 == findMember2)); //true 가 나옴

            // 트랜잭션 지원하는 쓰기 지원
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//            //선이 그어지고 쿼리가 두개 나감
//            // 위 두개 그때 마다 나가면 최적화 어려움이 있음
//            // 두개를 한꺼번에! Himbernate 에 기능이있
//            // batch_size 로서!!! -> 버퍼링 모아서 write하기!!
//            System.out.println("==========");

            // dirty checking
//            Member member = em.find(Member.class, 150L);
//            member.setName("zzzzz2");
//
////            em.persist(member); // 이거 해야할거같지만 할필요없음
//            //jpa 목적은 자바 컬렉션 처럼 이용하는것이 주요목적임
//            // 컬렉션 꺼내고 다시 넣을필요없는것처럼!!!
//            System.out.println("==========");

//            Member member = new Member(200L, "member200");
//            em.persist(member);
//            // 영컨에 담음
//            em.flush();
//            // 여기서 바로 db에 반영되어버림
//            System.out.println("==========");

         // 준영속 상태
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA"); // dirty checking

//            em.detach(member); // jpa에서 더이상 관리 안함!!!
            // 더이상 update 쿼리가 안나옴
            // 직접 쓸일은 X
            em.clear();

            // clear() 초기화 되어서 한번 더 쿼리문이 작성됨
            Member member2 = em.find(Member.class, 150L);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 커넥션 물고 동작하기 때문에 꼭 닫아 주어야함
        }
        emf.close(); // 팩토리 까지 닫음 (스프링이면 was내려갈 때 해주어야함)
    }
}
