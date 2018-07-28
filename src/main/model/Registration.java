package main.model;

public class Registration {


    String registrationNum;
    String studentNum;
    String courseNum;

    public String getRegistrationNum(){
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }
    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    @Override
    public String toString() {
        return  registrationNum + '/'
                + studentNum + '/'
                + courseNum + '/'
                + "\n";
    }
}
