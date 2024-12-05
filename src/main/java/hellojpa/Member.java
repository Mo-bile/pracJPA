package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "MEMBER")
// table 전략 -> 운영에서는 비권장
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ")
public class Member extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "USERNAME")
    private String username;

//    @ManyToOne(fetch = FetchType.LAZY) // 이걸로 lazy loading
    @ManyToOne(fetch = FetchType.EAGER) // 즉시
    // 읽기전용으로 함 | 일대다 양방향일 때
//    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToMany
    private List<MemberProduct> memberProducts = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}