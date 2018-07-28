package main.manager;

import main.dao.StudentDAO;
import main.model.Student;

import java.util.List;

public class StudentManager {
    public StudentDAO studentDAO;

    public StudentManager(){
        studentDAO = new StudentDAO();
    }

    public boolean dupleCheck(String studentRRN){
        return studentDAO.checkDupleStudent(studentRRN);
    }


    public boolean validationCheck(Student student){

        boolean isValid = true;
        if("".equals(student.getName())
                || "".equals(student.getEmail())
                || "".equals(student.getRRN())
                || "".equals(student.getPhoneNum())) {
            isValid = false;
        }
        if(student.getName().contains("/")
                || student.getEmail().contains("/")
                || student.getPhoneNum().contains("/")
                || student.getRRN().contains("/")) {
             isValid = false;
        }

        return isValid;
    }

    private String makeNewStudentNum() {
        return studentDAO.getNextStudentNum();
    }

    public boolean saveStudentInfo(Student newStudent) {
        if(!validationCheck(newStudent) || dupleCheck(newStudent.RRN)){
            return false;
        }
        newStudent.setStudentNum(makeNewStudentNum());
        return studentDAO.insertStudentInfo(newStudent);
    }
    public boolean modifyStudentInfo(Student targetStudent) {

        if(!validationCheck(targetStudent)){
            return false;
        }
        return studentDAO.updateStudentInfo(targetStudent);
    }

    public boolean removeStudentInfo(String targetStudentNum) {
        return studentDAO.deleteStudentInfo(targetStudentNum);
    }
    public Student inquireStudentInfo(String targetStudentNum) {

        return studentDAO.inquireStudentInfo(targetStudentNum);
    }

    public List<Student> inquireStudentsInfo(String targetStudentName){
        return studentDAO.inquireStudentsInfo(targetStudentName);
    }

    public List<Student> sortStudentsInfoByStudentNum() {
        return studentDAO.sortStudentsInfoByStudentNum();
    }

    public List<Student> sortStudentsInfoByName() {
        return studentDAO.sortStudentsInfoByName();
    }

    public List<Student> getAllStudentsInfo() {
        return studentDAO.getAllStudentsInfo();
    }
}
