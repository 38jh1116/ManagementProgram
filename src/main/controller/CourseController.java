package main.controller;

import main.manager.CourseManager;
import main.model.Course;
import main.view.CourseViewer;

import java.util.List;

public class CourseController {
    private CourseViewer courseViewer;
    private CourseManager courseManager;

    public CourseController() {
        courseViewer = new CourseViewer();
        courseManager = new CourseManager();
    }

    public void run() {
        boolean isBack = false;
        String selectedMenu = "";

        while (!isBack) {
            selectedMenu = courseViewer.showMenu();
            switch (selectedMenu) {
                case "0":
                    isBack = true;
                    break;
                case "1":
                    saveCourseInfo();
                    break;
                case "2":
                    inquireCourseInfo();
                    break;
                case "3":
                    modifyCourseInfo();
                    break;
                case "4":
                    removeCourseInfo();
                    break;
                default:
                    break;
            }
        }
    }

    private void saveCourseInfo() {

        Course newCourse = courseViewer.showCourseInfoInputCommand();
        if (courseManager.saveCourseInfo(newCourse)) {
            courseViewer.showSaveSuccessMessage();
        } else {
            courseViewer.showSaveFailMessage();
        }
    }

    private void inquireCourseInfo() {

       if(displayAllCoursesInfo()) return;
        boolean isBack = false;
        while (!isBack) {
            String inquireMenu = courseViewer.showInquireMenu();
            switch (inquireMenu) {
                case "1":
                    searchBySubjectNum();
                case "2":
                    searchByCourseName();
                    break;
                case "3":
                    searchByProfessorName();
                    break;
                case "4":
                    sortBySubjectNum();
                    break;
                case "5":
                    sortByCourseName();
                    break;
                case "0":
                    isBack = true;
                    break;
                default:
                    break;
            }
        }

    }

    private boolean displayAllCoursesInfo() {
        boolean isEmpty = false;
        List<Course> courseList = courseManager.getAllCoursesInfo();
        if (courseList.size() <= 0) {
            courseViewer.showNoDataMessage();
            isEmpty = true;
        }
        else{ courseViewer.showCoursesInfo(courseList);}
        return isEmpty;
    }

    private void searchBySubjectNum() {
        String subjectNum = courseViewer.showSubjectNumCommand();
        List<Course> targetCourses = courseManager.inquireCoursesInfoBySubjectNum(subjectNum);
        if (targetCourses.size() > 0) {
            courseViewer.showSuccessInquireMessage();
            courseViewer.showCoursesInfo(targetCourses);
        } else {
            courseViewer.showNotExistError();
        }
    }

    private void searchByProfessorName() {
        String professorName = courseViewer.showProfessorNameCommand();
        List<Course> targetCourses = courseManager.inquireCoursesInfoByProfessorName(professorName);
        if (targetCourses.size() > 0) {
            courseViewer.showSuccessInquireMessage();
            courseViewer.showCoursesInfo(targetCourses);
        } else {
            courseViewer.showNotExistError();
        }
    }

    private void searchByCourseName() {
        String courseName = courseViewer.showCourseNameCommand();
        List<Course> targetCourses = courseManager.inquireCoursesInfoByCourseName(courseName);

        if (targetCourses.size() > 0) {
            courseViewer.showSuccessInquireMessage();
            courseViewer.showCoursesInfo(targetCourses);
        } else {
            courseViewer.showNotExistError();
        }
    }

    private void sortBySubjectNum() {
        List<Course> sortedListBySubjectNum = courseManager.sortCoursesInfoBySubjectNum();
        courseViewer.showCoursesInfo(sortedListBySubjectNum);
    }

    private void sortByCourseName() {
        List<Course> sortedListByCourseName = courseManager.sortCoursesInfoByCourseName();
        courseViewer.showCoursesInfo(sortedListByCourseName);
    }

    private void modifyCourseInfo() {

        if(displayAllCoursesInfo()) return;
        String courseNum = courseViewer.showCourseNumCommand();
        Course targetCourse = courseManager.inquireCourseInfo(courseNum);

        if (targetCourse != null) {
            courseViewer.showCourseInfo(targetCourse);
            targetCourse = courseViewer.showModifyInfoCommand(targetCourse);
            if (courseManager.modifyCourseInfo(targetCourse)) {
                courseViewer.showModifySuccessMessage();
            } else {
                courseViewer.showModifyFailMessage();
            }
        } else {
            courseViewer.showNotExistError();
        }

    }

    private void removeCourseInfo() {
        if(displayAllCoursesInfo()) return;
        String courseNum = courseViewer.showCourseNumCommand();
        if (courseManager.removeCourseInfo(courseNum)){
            courseViewer.showRemoveSuccessMessage();
        } else {
            courseViewer.showRemoveFailMessage();
        }
    }
}