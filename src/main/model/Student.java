package main.model;

public class Student {
    public String studentNum;
    public String name;
    public String RRN;
    public String phoneNum;
    public String email;


    public Student(){

    }
    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public String getRRN() {
        return RRN;
    }

    public void setRRN(String RRN) {
        this.RRN = RRN;
    }
    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {

        return studentNum + "/"
                + name + "/"
                + RRN + "/"
                + phoneNum + "/"
                + email
                +"\n";
    }
}
