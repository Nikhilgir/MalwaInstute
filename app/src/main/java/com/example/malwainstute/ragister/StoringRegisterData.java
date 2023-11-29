package com.example.malwainstute.ragister;

public class StoringRegisterData {
    String username,department,rollno,password,phone;

    public StoringRegisterData() {
    }
    public StoringRegisterData(String username, String department, String rollno, String password, String phone) {
        this.username = username;
        this.department = department;
        this.rollno = rollno;
        this.password = password;
        this.phone=phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
