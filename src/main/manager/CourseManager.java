package main.manager;

import main.dao.CourseDAO;
import main.model.Course;

import java.util.List;

public class CourseManager {
    private CourseDAO courseDAO;

    public CourseManager(){
        courseDAO = new CourseDAO();
    }

    private boolean dupleCheck(Course course){
        return courseDAO.checkDupleCourse(course);
    }


    private boolean validationCheck(Course course){

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

    public List<Course> inquireCoursesInfoBySubjectNum(String subjectNum) {
        return courseDAO.inquireCoursesInfoBySubjectNum(subjectNum);

    }
    public List<Course> inquireCoursesInfoByCourseName(String targetSubjectName){
        return courseDAO.inquireCoursesInfoByCourseName(targetSubjectName);
    }
    public List<Course> inquireCoursesInfoByProfessorName(String targetCourseProfessorName){
        return courseDAO.inquireCoursesInfoByProfessorName(targetCourseProfessorName);
    }

    public List<Course> sortCoursesInfoBySubjectNum() {
        return courseDAO.sortCoursesInfoBySubjectNum();
    }

    public List<Course> sortCoursesInfoByCourseName() {
        return courseDAO.sortCoursesInfoByCourseName();
    }

    public List<Course> getAllCoursesInfo() {
        return courseDAO.getAllCoursesInfo();
    }


}
