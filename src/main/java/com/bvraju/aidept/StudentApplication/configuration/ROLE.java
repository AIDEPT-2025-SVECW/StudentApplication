package com.bvraju.aidept.StudentApplication.configuration;

import java.util.Set;

public enum ROLE {

    ADMIN(Set.of(PERMISSIONS.READ, PERMISSIONS.READ_SENSITIVE, PERMISSIONS.WRITE, PERMISSIONS.DELETE)),
    STUDENT(Set.of(PERMISSIONS.READ, PERMISSIONS.READ_SENSITIVE)),
    TEACHER(Set.of(PERMISSIONS.READ, PERMISSIONS.READ_SENSITIVE, PERMISSIONS.WRITE)),
    GUEST(Set.of(PERMISSIONS.READ));

    private Set<PERMISSIONS> permissions;

    private ROLE(Set<PERMISSIONS> varPermissions) {
        this.permissions = varPermissions;
    }

    public Set<PERMISSIONS> getPermissions() {
        return permissions;
    }

}
