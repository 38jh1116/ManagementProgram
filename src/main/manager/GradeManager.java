package main.manager;

import main.dao.CourseDAO;
import main.dao.RegistrationDAO;
import main.dao.StudentDAO;
import main.model.Course;
import main.model.Registration;

import java.util.*;

public class GradeManager {
    private RegistrationDAO registrationDAO;
    private CourseDAO courseDAO;
    private Map<String,Double> gradeMappingMap;
    private StudentDAO studentDAO;

    public GradeManager(){
        registrationDAO = new RegistrationDAO();
        studentDAO = new StudentDAO();
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
        if(totalCredit != 0) gradeAverage = gradeSum/totalCredit;
        gradeInfo.put("totalCredit", Integer.toString(totalCredit));
        gradeInfo.put("averageGrade", (String.format("%.2f",gradeAverage)));
        return gradeInfo;
    }

    private static Map<String, String> sortByValues(Map<String, String> targetMap ) {

        List<Map.Entry<String, String>> list = new LinkedList<Map.Entry<String, String>>(targetMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        for (Map.Entry<String, String> currentEntry : list)
        {
            sortedMap.put(currentEntry.getKey(),currentEntry.getValue());
        }

        return sortedMap;
    }

    public Map<String,String> inquireRegistrationInfoBySchoolYear(String targetYear) {
        targetYear = convertStudentNumToSchoolYear(targetYear);
        Map<String, List<Registration>> registrationsListByStudent = new TreeMap<>();
        registrationsListByStudent = registrationDAO.inquireRegistrationBySchoolYear(targetYear);

        Map<String, String> studentGradeList = new TreeMap<>();

        for (Map.Entry<String, List<Registration>> currentEntry : registrationsListByStudent.entrySet()) {
            String currentStudentNum =  currentEntry.getKey();
            List<Registration> currentStudentRegistrationList = currentEntry.getValue();

            Map<String,String> calcResult = new TreeMap<>();
            calcResult = calcTotalGrade(currentStudentRegistrationList);

            studentGradeList.put(currentStudentNum,calcResult.get("averageGrade"));
        }
        studentGradeList = sortByValues(studentGradeList);
        return studentGradeList;

    }

    private String convertStudentNumToSchoolYear(String targetYear) {
        int thisYearStartNum = Integer.parseInt(studentDAO.getNextStudentNum(false).substring(0,2));
        return String.valueOf(thisYearStartNum - Integer.parseInt(targetYear) + 1);
    }
}
