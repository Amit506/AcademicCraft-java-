package com.example.Auth.Config;

import lombok.Getter;

@Getter
public enum UserRole {

    ROLE_DEFAULT("ROLE_DEFAULT",1),
    ROLE_ADMIN("ROLE_ADMIN",4);

    private final String role;
    private final Integer roleId;

    UserRole(String role,Integer roleId) {
        this.role = role;
        this.roleId=roleId;
    }
    public static UserRole valueOfOrElse(String name) {
        for (UserRole value : values()) {
            if (value.name().equalsIgnoreCase("ROLE_"+name)) {
                return value;
            }
        }
        return ROLE_DEFAULT;
    }

    @Override
    public String toString() {
          return this.role + " : " + this.roleId;
    }
}
