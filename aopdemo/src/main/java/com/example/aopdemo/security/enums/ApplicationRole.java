package com.example.aopdemo.security.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationRole {
    ADMIN(Sets.newHashSet(ApplicationPermission.READ, ApplicationPermission.WRITE)),
    MANAGER(Sets.newHashSet(ApplicationPermission.READ, ApplicationPermission.WRITE)),
    SALES(Sets.newHashSet(ApplicationPermission.READ));

    private Set<ApplicationPermission> applicationPermissions;

    ApplicationRole(Set<ApplicationPermission> applicationPermissions) {
        this.applicationPermissions = applicationPermissions;
    }

    public Set<ApplicationPermission> getApplicationPermissions() {
        return applicationPermissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        return applicationPermissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.name()))
                .collect(Collectors.toSet());
    }
}
