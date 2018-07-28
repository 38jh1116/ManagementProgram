package main.manager;

import main.dao.CourseDAO;
import main.model.Course;
import main.model.Professor;
import main.model.Subject;

import java.util.List;

public class CourseManager {
    private CourseDAO courseDAO;

    public CourseManager(){

        courseDAO = new CourseDAO();
    }

    private boolean checkDuple(Course course){
        return courseDAO.checkDupleCourse(course);
    }

    private boolean checkValidation(Course course){

        boolean isValid = true;
        if( "".equals(course.getSubject().getSubjectNum())
                || "".equals(course.getClassNum())
                || "".equals(course.getProfessor().getProfessorNum())
                || "".equals(course.getSemester())
                || "".equals(course.getTime())) {
            isValid = false;
        }
        if(course.getSubject().getSubjectNum().contains("/")
                || course.getClassNum().contains("/")
                || course.getProfessor().getProfessorNum().contains("/")
                || course.getSemester().contains("/")
                || course.getTime().contains("/")){
            isValid = false;
        }

        return isValid;
    }
    private boolean checkProfessor(Professor professor) {
        return courseDAO.findProfessor(professor.getProfessorNum());
    }
    private boolean checkSubject(Subject subject) {
        return courseDAO.findSubject(subject.getSubjectNum());
    }

    private String makeNewCourseNum() {
        return courseDAO.getNextCourseNum();
    }

    public boolean saveCourseInfo(Course newCourse) {
        if(!checkValidation(newCourse) || checkDuple(newCourse)){
            return false;
        }
        if(!checkSubject(newCourse.getSubject())  || !checkProfessor(newCourse.getProfessor()) ) {
            return false;
        }
        newCourse.setCourseNum(makeNewCourseNum());
        return courseDAO.insertCourseInfo(newCourse);
    }


    public boolean modifyCourseInfo(Course targetCourse) {

        if(!checkValidation(targetCourse)){
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
    public List<Course> inquireCoursesInfoBySubjectName(String targetSubjectName){
        return courseDAO.inquireCoursesInfoBySubjectName(targetSubjectName);
    }
    public List<Course> inquireCoursesInfoByProfessorName(String targetCourseProfessorName){
        return courseDAO.inquireCoursesInfoByProfessorName(targetCourseProfessorName);
    }

    public List<Course> sortCoursesInfoBySubjectNum() {
        return courseDAO.sortCoursesInfoBySubjectNum();
    }

    public List<Course> sortCoursesInfoBySubjectName() {
        return courseDAO.sortCoursesInfoBySubjectName();
    }

    public List<Course> getAllCoursesInfo() {

        return courseDAO.getAllCoursesInfo();
    }

}
