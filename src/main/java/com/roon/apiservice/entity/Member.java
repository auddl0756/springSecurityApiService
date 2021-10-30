package com.roon.apiservice.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Entity
public class Member {
    @Id
    private String email;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    MemberRole memberRole;

    public void setMemberRole(MemberRole memberRole){
        this.memberRole = memberRole;
    }
}
