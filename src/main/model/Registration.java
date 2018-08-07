package main.model;

public class Registration {

    private Student student;
    private Course course;
    private String grade;
    private String isRetake;
    private String registrationNum;

    public Registration(){
        student = new Student();
        course = new Course();
    }
    public String getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getIsRetake() {
        return isRetake;
    }

    public void setIsRetake(String isRetake) {
        this.isRetake = isRetake;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public void setStudentNum(String studentNum){
        this.student.setStudentNum(studentNum);
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public void setCourseNum(String courseNum){
        this.course.setCourseNum(courseNum);
    }


    @Override
    public String toString() {
        return registrationNum + '/'
                + student.getStudentNum() + '/'
                + course.getCourseNum()+ '/'
                + grade + '/'
                + isRetake
                + "\n";
    }
}
