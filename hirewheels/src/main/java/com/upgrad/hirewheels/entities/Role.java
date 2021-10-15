package com.upgrad.hirewheels.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Role {

    @Id
    @Column(length = 10)
    @NonNull
    private int roleId;

    @Column(length = 50, nullable = false, unique = true)
    @NonNull
    private String roleName;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", users=" + users.stream().map(User::getUserId).collect(Collectors.toSet()) +
                '}';
    }
}
