package main.manager;

import main.dao.CourseDAO;
import main.model.Course;

import java.util.List;

public class CourseManager {
    public CourseDAO courseDAO;

    public CourseManager(){
        courseDAO = new CourseDAO();
    }

    public boolean dupleCheck(Course course){
        return courseDAO.checkDupleCourse(course);
    }


    public boolean validationCheck(Course course){

        boolean isValid = true;
        if("".equals(course.getCourseName())
                || "".equals(course.getSubjectNum())
                || "".equals(course.getClassNum())
                || "".equals(course.getProfessorId())
                || "".equals(course.getSemester())
                || "".equals(course.getTime())
                || "".equals(course.getCredit())) {
            isValid = false;
        }
        if(course.getCourseName().contains("/")
                || course.getSubjectNum().contains("/")
                || course.getClassNum().contains("/")
                || course.getProfessorId().contains("/")
                || course.getSemester().contains("/")
                || course.getTime().contains("/")
                || course.getCredit().contains("/")){
            isValid = false;
        }

        return isValid;
    }

    private String makeNewCourseNum() {
        return courseDAO.getNextCourseNum();
    }

    public boolean saveCourseInfo(Course newCourse) {
        if(!validationCheck(newCourse) || dupleCheck(newCourse)){
            return false;
        }
        newCourse.setCourseNum(makeNewCourseNum());
        return courseDAO.insertCourseInfo(newCourse);
    }
    public boolean modifyCourseInfo(Course targetCourse) {

        if(!validationCheck(targetCourse)){
            return false;
        }
        return courseDAO.updateCourseInfo(targetCourse);
    }

    public boolean removeCourseInfo(String targetCourseNum) {
        return courseDAO.deleteCourseInfo(targetCourseNum);
    }
    public Course inquireCourseInfo(String targetCourseNum) {

        return courseDAO.inquireCourseInfo(targetCourseNum);
    }

    public List<Course> inquireCoursesBySubjectNum(String subjectNum) {
        return courseDAO.inquireCoursesBySubjectNum(subjectNum);

    }
    public List<Course> inquireCoursesByCourseName(String targetSubjectName){
        return courseDAO.inquireCoursesByCourseName(targetSubjectName);
    }
    public List<Course> inquireCoursesByProfessorName(String targetCourseProfessorName){
        return courseDAO.inquireCoursesByProfessorName(targetCourseProfessorName);
    }

    public List<Course> sortCoursesBySubjectNum() {
        return courseDAO.sortCoursesBySubjectNum();
    }

    public List<Course> sortCoursesByCourseName() {
        return courseDAO.sortCoursesByCourseName();
    }

    public List<Course> getAllCoursesInfo() {
        return courseDAO.getAllCoursesInfo();
    }


}
