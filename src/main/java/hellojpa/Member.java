package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // jpa사용하는 얘로 하기
//@Table(name = "USER") // 쿼리가 유저 테이블에 인서트함
public class Member {

    @Id
    private Long id;

//    @Column(name = "usename")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
