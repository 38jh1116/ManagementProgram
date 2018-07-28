package main.controller;

import main.manager.StudentManager;
import main.model.Student;
import main.view.StudentViewer;

import java.util.List;

public class StudentController {
    private StudentViewer studentViewer;
    private StudentManager studentManager;

    public StudentController(){
        studentViewer = new StudentViewer();
        studentManager = new StudentManager();

    }
    public void run() {
        boolean isBack = false;
        String selectedMenu = "";

        while(!isBack){
            selectedMenu =  studentViewer.showMenu();
            switch (selectedMenu) {
                case "0":
                    isBack = true;
                    break;
                case "1":
                    saveStudentInfo();
                    break;
                case "2":
                    inquireStudentInfo();
                    break;
                case "3":
                    modifyStudentInfo();
                    break;
                case "4":
                    removeStudentInfo();
                    break;
                default:
                    break;
            }
        }
    }
    private void saveStudentInfo(){

        Student newStudent = studentViewer.showStudentInfoInputCommand();

        if(studentManager.saveStudentInfo(newStudent)){
            studentViewer.showSaveSuccessMessage();
        }else{
            studentViewer.showSaveFailMessage();
        }
    }
    private void inquireStudentInfo() {

        if(displayAllStudentsInfo()) return;
        boolean isBack = false;
        while(!isBack){
            String inquireMenu = studentViewer.showInquireMenu();
            switch (inquireMenu){
                case "1" :
                    searchByStudentNumber();
                    break;
                case "2" :
                    searchByStudentName();
                    break;
                case "3" :
                    sortByStudentNumber();
                    break;
                case "4" :
                    sortByStudentName();
                    break;
                case "0":
                    isBack = true;
                    break;
                default:
                    break;
            }
        }

    }
    private void modifyStudentInfo() {
        if(displayAllStudentsInfo()) return;
        String studentNum = studentViewer.showStudentNumCommand();
        Student targetStudent = studentManager.inquireStudentInfo(studentNum);

        if(targetStudent != null){
            studentViewer.showStudentInfo(targetStudent);
            targetStudent = studentViewer.showModifyInfoCommand(targetStudent);
            if(studentManager.modifyStudentInfo(targetStudent)){
                studentViewer.showModifySuccessMessage();
            }
            else{
                studentViewer.showModifyFailMessage();
            }
        }else{
            studentViewer.showNotExistError();
        }

    }

    private void removeStudentInfo() {
       if(displayAllStudentsInfo()) return;

        String studentNum = studentViewer.showStudentNumCommand();
        if (studentManager.removeStudentInfo(studentNum)) {
            studentViewer.showRemoveSuccessMessage();
        } else {
            studentViewer.showRemoveFailMessage();
        }
    }

    private boolean displayAllStudentsInfo(){
        boolean isEmpty = false;
        List<Student> studentList = studentManager.getAllStudentsInfo();
        if(studentList.size() <= 0){
            studentViewer.showNoDataMessage();
            isEmpty = true;
        }
        else{studentViewer.showStudentsInfo(studentList);}
        return isEmpty;
    }

    private void searchByStudentNumber() {
        String studentNum = studentViewer.showStudentNumCommand();
        Student targetStudent = studentManager.inquireStudentInfo(studentNum);
        if(targetStudent != null){
            studentViewer.showSuccessInquireMessage();
            studentViewer.showStudentInfo(targetStudent);
        }
        else{
            studentViewer.showNotExistError();
        }
    }

    private void searchByStudentName() {
        String studentName = studentViewer.showStudentNameCommand();
        List<Student> targetStudents = studentManager.inquireStudentsInfoByName(studentName);

        if(targetStudents.size() > 0){
            studentViewer.showSuccessInquireMessage();
            studentViewer.showStudentsInfo(targetStudents);
        }
        else{
            studentViewer.showNotExistError();
        }
    }

    private void sortByStudentNumber() {
        List<Student> sortedListByStudentNumber = studentManager.sortStudentsInfoByStudentNum();
        studentViewer.showStudentsInfo(sortedListByStudentNumber);
    }
    private void sortByStudentName() {
        List<Student> sortedListByStudentName = studentManager.sortStudentsInfoByName();
        studentViewer.showStudentsInfo(sortedListByStudentName);
    }
}