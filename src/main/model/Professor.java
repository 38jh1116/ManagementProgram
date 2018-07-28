package main.model;

public class Professor {

    public String professorNum;
    public String name;
    public String RRN;
    public String phoneNum;
    public String email;
    public String officeAddress;
    public String officeNum;




    public Professor() {

    }

    public String getProfessorNum() {
        return professorNum;
    }

    public void setProfessorNum(String professorNum) {
        this.professorNum = professorNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRRN() {
        return RRN;
    }

    public void setRRN(String RRN) {
        this.RRN = RRN;
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

    public String getOfficeNum() {
        return officeNum;
    }

    public void setOfficeNum(String officeNum) {
        this.officeNum = officeNum;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }
    @Override
    public String toString() {
        return  professorNum + '/'
                + name + '/'
                + RRN + '/'
                + phoneNum + '/'
                + email + '/'
                + officeAddress + '/'
                + officeNum+ '\n';
    }
}
