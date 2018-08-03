package main.manager;

import main.Message;
import main.dao.CourseDAO;
import main.dao.RegistrationDAO;
import main.model.Course;
import main.model.Registration;
import main.model.Student;

import java.util.List;

public class RegistrationManager {
    private RegistrationDAO registrationDAO;
    private CourseDAO courseDAO;

    public RegistrationManager(){
        registrationDAO = new RegistrationDAO();
        courseDAO = new CourseDAO();
    }

    private String checkPossibility(Registration registration){
        return registrationDAO.checkPossibility(registration);
    }
    private boolean checkStudent(Student student){
        return registrationDAO.checkStudent(student.getStudentNum());
    }
    private boolean checkCourse(Course course){
        return registrationDAO.checkCourse(course.getCourseNum());
    }
    private String makeNewRegistrationNum() {
        return registrationDAO.getNextRegistrationNum();
    }

    private boolean validationCheck(Registration registration){

        boolean isValid = true;
        if("".equals(registration.getStudent().getStudentNum()) || "".equals(registration.getCourse().getCourseNum())){
            isValid = false;
        }
        if(registration.getStudent().getStudentNum().contains("/") || registration.getCourse().getCourseNum().contains("/")){
            isValid = false;
        }

        return isValid;
    }

    public boolean saveRegistrationInfo(Registration newRegistration) {
        if(validationCheck(newRegistration)
                && checkStudent(newRegistration.getStudent())
                && checkCourse(newRegistration.getCourse())){
            String takeCase = checkPossibility(newRegistration);
            if(takeCase.equals(Message.IMPOSSIBLE)) return false;
            else{
                newRegistration.setRegistrationNum(makeNewRegistrationNum());
                newRegistration.setGrade(Message.NOT_YET);
                newRegistration.setIsRetake(takeCase);
            }
        }
        else return false;
        return registrationDAO.insertRegistrationInfo(newRegistration);
    }
    public boolean modifyRegistrationInfo(Registration targetRegistration) {

        return validationCheck(targetRegistration)
                && registrationDAO.updateRegistrationInfo(targetRegistration);
    }

    public boolean removeRegistrationInfo(String targetRegistrationNum) {
        return registrationDAO.deleteRegistrationInfo(targetRegistrationNum);
    }


    public List<Registration> inquireRegistrationsByStudentNum(String targetRegistrationStudentNum){
        return registrationDAO.inquireRegistrationsByStudentNum(targetRegistrationStudentNum);
    }

    public List<Registration> inquireRegistrationsByCourseNum(String targetRegistrationCourseNum) {
        return registrationDAO.inquireRegistrationsByCourseNum(targetRegistrationCourseNum);
    }


    public List<Course> getAllCourseInfo() {
        return courseDAO.getAllCoursesInfo();
    }
}
