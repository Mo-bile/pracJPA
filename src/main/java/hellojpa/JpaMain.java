package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        //heelo 는 persistence.xml의 설정 정보 들고오는것
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // emf는 앱 로딩 시점에 딱 하나만 만들어야함
        // 요청마다 생김 -> 그래서 조심해야함 -> 쓰레드간 공유하면 절대로 안됨
        EntityManager em = emf.createEntityManager(); // db커넥션 받안다고 생각해
        //실제 저장하는 트랜잭션 단위는 db커넥션얻어서 쿼리날리고 종료하는건 매니저로 함

        EntityTransaction tx = em.getTransaction(); // 모든 변경은 트랜잭션 안에서 해야함
        tx.begin();
        //트랜잭션은 Try, catch 에 넣는것이 바람직
        try {
            //code -> 여기서 부터 각종 작업이 진행됨

            // 등록
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

            // 조회
//            Member findMember = em.find(Member.class, 2L); // 자바 커넥션처럼 이해하면 편함
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

            // 삭제
//            Member findMember = em.find(Member.class, 2L);
//            em.remove(findMember);

            // 수정
            // jpa로 entity 를 가져오면 jpa가 관리를 함 -> 변경여부에 대해서 확인해서 쿼리만듬
//            Member findMember = em.find(Member.class, 2L);
//            findMember.setName("HelloJPA"); //이렇게 em.persist 처럼에다가 수정하고 저장 안해도 됨!

            // JPQL -> 어떤 메리트임? 페이징 원할 시
            // 대상이 테이블이 아니라 객체임
            List<Member> result = em.createQuery("select m from Member as m ", Member.class)
                    // 페이지네이션 할 때 -> 방언에 따라서 만들어지는 쿼리가 바뀜
                    // 객체지향 쿼리
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member = " + member.getName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 커넥션 물고 동작하기 때문에 꼭 닫아 주어야함
        }
        emf.close(); // 팩토리 까지 닫음 (스프링이면 was내려갈 때 해주어야함)
    }
}
