package com.example.restaurantsystem.entity.repository;

public enum Role {

    ADMIN("Admin"),
    USER("User");

    String roleText;

    Role(String roleText){
        this.roleText = roleText;
    }
    public String getRoleText() {
        return roleText;
    }
}
