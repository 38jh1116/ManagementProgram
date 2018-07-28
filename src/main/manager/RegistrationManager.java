package main.manager;

import main.dao.CourseDAO;
import main.dao.RegistrationDAO;
import main.dao.StudentDAO;
import main.model.Registration;

import java.util.List;

public class RegistrationManager {
    public RegistrationDAO registrationDAO;
    public StudentDAO studentDAO;
    public CourseDAO courseDAO;

    public RegistrationManager(){
        registrationDAO = new RegistrationDAO();
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
    }

    public boolean dupleCheck(Registration registration){
        return registrationDAO.checkDupleRegistration(registration);
    }
    public boolean existCheck(Registration registration){
        return (studentDAO.findStudent(registration.getStudentNum()) && courseDAO.findCourse(registration.getCourseNum()));
    }

    public boolean validationCheck(Registration registration){

        boolean isValid = true;
        if("".equals(registration.getStudentNum()) || "".equals(registration.getCourseNum())){
            isValid = false;
        }
        if(registration.getStudentNum().contains("/") || registration.getCourseNum().contains("/")){
            isValid = false;
        }

        return isValid;
    }

    private String makeNewRegistrationNum() {
        return registrationDAO.getNextRegistrationNum();
    }

    public boolean saveRegistrationInfo(Registration newRegistration) {
        if(!validationCheck(newRegistration) || dupleCheck(newRegistration) || !existCheck(newRegistration)){
            return false;
        }
        newRegistration.setRegistrationNum(makeNewRegistrationNum());
        return registrationDAO.insertRegistrationInfo(newRegistration);
    }
    public boolean modifyRegistrationInfo(Registration targetRegistration) {

        if(!validationCheck(targetRegistration)){
            return false;
        }
        return registrationDAO.updateRegistrationInfo(targetRegistration);
    }

    public boolean removeRegistrationInfo(String targetRegistrationNum) {
        return registrationDAO.deleteRegistrationInfo(targetRegistrationNum);
    }

    public Registration inquireRegistrationInfo(String targetRegistrationNum) {

        return registrationDAO.inquireRegistrationInfo(targetRegistrationNum);
    }

    public List<Registration> inquireRegistrationsByStudentNum(String targetRegistrationStudentNum){
        return registrationDAO.inquireRegistrationsByStudentNum(targetRegistrationStudentNum);
    }

    public List<Registration> inquireRegistrationsByCourseNum(String targetRegistrationCourseNum) {
        return registrationDAO.inquireRegistrationsByCourseNum(targetRegistrationCourseNum);
    }

    public List<Registration> sortRegistrationsByStudentNum() {
        return registrationDAO.sortRegistrationsInfoByStudent();
    }

    public List<Registration> sortRegistrationsByCourseNum() {
        return registrationDAO.sortRegistrationsInfoByCourse();
    }

    public List<Registration> getAllRegistrationsInfo() {
        return registrationDAO.getAllRegistrationsInfo();
    }


}
