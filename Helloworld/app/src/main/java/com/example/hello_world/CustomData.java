package com.example.hello_world;

public class CustomData {

        private String name;
        private String department;
        private String phone;
        private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CustomData(String name, String department, String phone, String email) {
        this.name = name;
        this.department = department;
        this.phone = phone;
        this.email = email;
    }
}


