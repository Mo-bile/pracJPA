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

    @ManyToOne
    // 읽기전용으로 함 | 일대다 양방향일 때
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<Product> productList = new ArrayList<>();




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

}