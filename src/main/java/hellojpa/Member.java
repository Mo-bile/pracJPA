package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "MEMBER")
public class Member{
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "USERNAME")
    private String username;

    // 기간 Period
    @Embedded
    private Period workperiod;
    // 주소 address
    @Embedded
    private Address homeAddress;

    public Period getWorkperiod() {
        return workperiod;
    }

    public void setWorkperiod(Period workperiod) {
        this.workperiod = workperiod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public List<MemberProduct> getMemberProducts() {
        return memberProducts;
    }

    public void setMemberProducts(List<MemberProduct> memberProducts) {
        this.memberProducts = memberProducts;
    }

    @ManyToOne(fetch = FetchType.EAGER) // 즉시
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