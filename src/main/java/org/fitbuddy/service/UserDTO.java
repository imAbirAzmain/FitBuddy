package org.fitbuddy.service;

public class UserDTO {
    public String name;
    public int age;
    public double heightCm;
    public String goal;

    public UserDTO(String name, int age, double heightCm, String goal) {
        this.name = name;
        this.age = age;
        this.heightCm = heightCm;
        this.goal = goal;
    }
}
