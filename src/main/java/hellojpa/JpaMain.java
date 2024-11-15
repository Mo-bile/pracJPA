package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // db커넥션 받안다고 생각해
        EntityTransaction tx = em.getTransaction(); // 모든 변경은 트랜잭션 안에서 해야함

        tx.begin();
        try {

            // Create : 입력시
            // 연관관계 매핑 없는 방식으로!!!
            //
//            select * from member a
//            join team t on a.team_id = t.team_id;

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member");
//            member.setTeamId(team.getTeamId());
            // 연관관계 매핑방식
            member.setTeam(team); //단방향 연관관계, 참조 저장

            em.persist(member);

            // read 방식
            // 하지만 쿼리안나감 : 영속성 컨텍스트 1차 캐시에서 들고옴
            Member findMember = em.find(Member.class, member.getId());
            // 비워내기
//            em.flush();

            // 참조를 사용해서 연관관계 조회
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam.getName() = " + findTeam.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}