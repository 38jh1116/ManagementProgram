package main.manager;

import main.dao.CourseDAO;
import main.dao.RegistrationDAO;
import main.model.Course;
import main.model.Registration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeManager {
    RegistrationDAO registrationDAO;
    CourseDAO courseDAO;
    Map<String,Double> gradeMappingMap;

    public GradeManager(){
        registrationDAO = new RegistrationDAO();
        courseDAO = new CourseDAO();
        gradeMappingMap = new HashMap<>();
        gradeMapInit();
    }
    private void gradeMapInit(){
        gradeMappingMap.put("A+",4.5);
        gradeMappingMap.put("A0",4.0);
        gradeMappingMap.put("B+",3.5);
        gradeMappingMap.put("B0",3.0);
        gradeMappingMap.put("C+",2.5);
        gradeMappingMap.put("C0",2.0);
        gradeMappingMap.put("D+",1.5);
        gradeMappingMap.put("D0",1.0);
        gradeMappingMap.put("F",0.0);
        gradeMappingMap.put("NY",-1.0);
    }
    public boolean saveGradeInfo(List<Registration> registrationList){
        return registrationDAO.saveGradeInfo(registrationList);
    }

    public List<Registration> getRegistrationInfoInCourse(String targetCourseNum) {
        return registrationDAO.inquireRegistrationsByCourseNum(targetCourseNum);
    }

    public List<Registration> inquireRegistrationInfoByStudentNum(String studentNum) {
        return registrationDAO.inquireRegistrationsByStudentNum(studentNum);
    }

    public List<Registration> inquireRegistrationInfoByCourseNum(String courseNum) {
        return registrationDAO.inquireRegistrationsByCourseNum(courseNum);
    }

    public List<Course> getCoursesInfo(String targetProfessorNum) {
        return courseDAO.inquireCoursesInfoByProfessorNum(targetProfessorNum);
    }

    public Map<String,String> calcTotalGrade(List<Registration> targetRegistrationList) {
        Map<String, String> gradeInfo = new HashMap<>();
        int totalCredit=0;
        double gradeSum=0;
        double gradeAverage=0;

        for(Registration registration : targetRegistrationList){
            int currentCredit = Integer.parseInt(registration.getCourse().getSubject().getCredit());
            double currentGrade = gradeMappingMap.get(registration.getGrade());
            if(currentGrade == -1.0 ) {
                currentGrade = 0.0;
            }
            else{
                totalCredit += currentCredit;
            }
            gradeSum += currentCredit * currentGrade;
        }
        gradeAverage = gradeSum/totalCredit;
        gradeInfo.put("totalCredit", Integer.toString(totalCredit));
        gradeInfo.put("averageGrade", Double.toString(gradeAverage));
        return gradeInfo;
    }

}
