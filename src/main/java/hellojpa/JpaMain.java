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

            // 이 순서로 하면 주인의 값이 입력이 안된상태
//            Member member = new Member();
//            member.setUsername("member");
//            em.persist(member);
//
//            Team team = new Team();
//            team.setName("TeamA");
//            team.getMembers().add(member);
//            em.persist(team);

            //저장
            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(member);
            em.persist(team);

            Member member = new Member();
            member.setUsername("member");
            member.setTeam(team); // 주인에 값을 넣기
            em.persist(member);

            //이거를 그냥 setter에 넣어주자
//            team.getMembers().add(member);

//            em.flush();
//            em.clear();

            //1차 캐시 flush, clear 안하면
            Team findTeam = em.find(Team.class, team.getTeamId());
            List<Member> members = findTeam.getMembers();

            System.out.println(" =========== ");
            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }
            System.out.println(" =========== ");


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}