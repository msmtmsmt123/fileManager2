package com.entity;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password_hash;

    public UserDTO mapFromEntity(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword_hash(user.getPassword_hash());
        return userDTO;
    }

    public User mapToEntity(UserDTO userDTO){
        return new User(userDTO.getId(),userDTO.getName(),userDTO.getEmail(),userDTO.getUsername(),userDTO.getPassword_hash());
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

  }
