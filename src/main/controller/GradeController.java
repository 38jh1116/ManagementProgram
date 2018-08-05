package main.controller;

import main.manager.GradeManager;
import main.model.Course;
import main.model.Registration;
import main.view.GradeViewer;

import java.util.List;
import java.util.Map;

public class GradeController {
    private GradeViewer gradeViewer;
    private GradeManager gradeManager;

    public GradeController() {
        gradeViewer = new GradeViewer();
        gradeManager = new GradeManager();
    }

    public void run() {
        boolean isBack = false;
        String selectedMenu = "";

        while (!isBack) {
            selectedMenu = gradeViewer.showMenu();
            switch (selectedMenu) {
                case "1":
                    saveGradeInfo();
                    break;
                case "2":
                    inquireGradeInfo();
                    break;
                case "0":
                    isBack = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void saveGradeInfo() {

        String targetProfessorNum = gradeViewer.showProfessorNumCommand();
        List<Course> targetProfessorCourses = gradeManager.getCoursesInfo(targetProfessorNum);
        if(targetProfessorCourses.size() == 0){
            gradeViewer.showNoDataMessage();
            return;
        }
        gradeViewer.showCoursesInfo(targetProfessorCourses);

        gradeViewer.showCourseNumCommandMessage();
        String targetCourseNum = gradeViewer.showCourseNumCommand();

        List<Registration> registrationsList = gradeManager.getRegistrationInfoInCourse(targetCourseNum);
        if(registrationsList.size() == 0 ){
            gradeViewer.showNoDataMessage();
            return;
        }
        gradeViewer.showGradeInputCommandMessage();
        for(Registration registration : registrationsList){
            String targetGrade = gradeViewer.showGradeInputCommand(registration.getStudent().getStudentNum());
            if(!"".equals(targetGrade)){
                registration.setGrade(targetGrade);
            }
        }
        if(gradeManager.saveGradeInfo(registrationsList)){
            gradeViewer.showSaveSuccessMessage();
        }else{
            gradeViewer.showSaveFailMessage();
        }
    }

    private void inquireGradeInfo() {

        boolean isBack = false;
        while (!isBack) {
            String inquireMenu = gradeViewer.showInquireMenu();
            switch (inquireMenu) {
                case "1":
                    searchByStudentNum();
                    break;
                case "2":
                    searchByCourseNum();
                    break;
                case "3":
                    searchByGrade();
                    break;

                case "0":
                    isBack = true;
                    break;
                default:
                    break;
            }
        }
    }



    private void searchByStudentNum() {
        String studentNum = gradeViewer.showStudentNumCommand();
        List<Registration> targetRegistration = gradeManager.inquireRegistrationInfoByStudentNum(studentNum);
        if (targetRegistration.size() > 0) {
            Map<String,String> gradeInfo = gradeManager.calcTotalGrade(targetRegistration);
            gradeViewer.showGradesInfoByStudent(targetRegistration,gradeInfo);
        } else {
            gradeViewer.showNoDataMessage();
        }
    }

    private void searchByCourseNum() {
        String courseNum = gradeViewer.showCourseNumCommand();
        List<Registration> targetRegistration = gradeManager.inquireRegistrationInfoByCourseNum(courseNum);
        if (targetRegistration.size() > 0) {
            gradeViewer.showGradesInfoByCourse(targetRegistration);
        } else {
            gradeViewer.showNoDataMessage();
        }
    }

    private void searchByGrade() {
    }

}
