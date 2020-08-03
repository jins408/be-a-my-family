// 하단 DB 설정 부분은 Sub PJT II에서 데이터베이스를 구성한 이후에 주석을 해제하여 사용.

package com.web.blog.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    private String uid;

    @JsonIgnore
    private String name;
    private String password;
    private String email;
    private String phone;
    private String job;
    private boolean marriaged;  //true = 기혼, false = 미혼
    private boolean sex;    //true = 남자, false = 여자
    private String birthdate;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdate;

}
