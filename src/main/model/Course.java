package main.model;

public class Course {
    private String courseNum;
    private String subjectNum;
    private String courseName;
    private String classNum;
    private String professorId;
    private String semester;
    private String time;
    private String credit;


    public Course() {

    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getSubjectNum() {
        return subjectNum;
    }

    public void setSubjectNum(String subjectNum) {
        this.subjectNum = subjectNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
    @Override
    public String toString() {
        return courseNum + '/'
                + subjectNum + '/'
                + courseName + '/'
                + classNum + '/'
                + professorId + '/'
                + semester + '/'
                + time + '/'
                + credit +
                '\n';
    }



}
