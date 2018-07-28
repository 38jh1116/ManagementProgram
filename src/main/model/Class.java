package main.model;

public class Class {
    public String classNum;
    public String name;
    public String professorId;
    public String semester;

    public Class(String classNum, String name, String professorId, String semester) {
        this.classNum = classNum;
        this.name = name;
        this.professorId = professorId;
        this.semester = semester;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return  classNum + " "
                + name + " "
                + professorId + " "
                + semester
                + "\n";
    }
}
