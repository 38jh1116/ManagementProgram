package main.view;

import main.Message;
import main.MyPrinter;
import main.MyScanner;
import main.model.Course;
import main.model.Registration;
import main.model.Subject;

import java.util.List;
import java.util.Map;


public class GradeViewer extends MyPrinter{
    public String showMenu() {
        print("\n############## [6] 성적관리 ############\n");
        print("\n 1. 성적 입력 및 수정\n");
        print(" 2. 성적 조회  \n");
        print("-------------------\n");
        print(" 0. 뒤로 가기 \n\n");
        print(">> ");
        return MyScanner.stringScanner();
    }
    public String showInquireMenu(){
        print("\n 1. 학생별 조회 \n");
        print(" 2. 강의별 조회 \n");
        print(" 3. 성적순 조회 \n");
        print("------------------\n");
        print(" 0. 뒤로 가기\n\n");
        print(">> ");
        return MyScanner.stringScanner();
    }
    public String showStudentNumCommand() {
        print("\n학번 >> ");
        return MyScanner.stringScanner();

    }
    public void showCourseNumCommandMessage(){
        print("\n성적을 입력할 강의 번호를 입력하세요");
    }
    public String showCourseNumCommand(){
        print("\n강의 번호 >> ");
        return MyScanner.stringScanner();
    }
    public void showGradeInputCommandMessage(){
        print("\n\n성적을 입력하세요. (변경 사항 없을 시 : Enter)\n");
        print("[학번]\t\t[성적]\n");
    }
    public String showGradeInputCommand(String registrationStudentNum) {
        print(registrationStudentNum + "  :  ");
        return MyScanner.gradeScanner();
    }

    public void showNotExistError() {
        print("\n"+ Message.NOT_EXIST_ERROR+"\n");
    }

    public void showSaveSuccessMessage() {
        print("\n"+Message.SAVE_SUCCESS + "a new grade information\n");
    }

    public void showSaveFailMessage() {
        print("\n"+Message.SAVE_FAIL + "a new grade information\n");
    }

    public void showModifySuccessMessage() {
        print("\n"+Message.MODIFY_SUCCESS + "the grade information\n");
    }

    public void showModifyFailMessage() {
        print("\n"+Message.MODIFY_FAIL + "the grade information\n");
    }

    public void showSuccessInquireMessage() {
        print(Message.INQUIRE_SUCCESS + "the grade information\n");
    }
    public void showNoDataMessage(){
        print("\n"+Message.NO_DATA + "in grade file\n");
    }


    public void showCoursesInfo(List<Course> courseList) {
        print("\n[강의번호]\t\t[과목번호]\t  [강의명]\t\t\t [분반] [강의학기]\n");
        for(Course course : courseList){
            printf("%-12s",course.getCourseNum());
            printf("%-10s",course.getSubject().getSubjectNum());
            printf("%-20s",course.getSubject().getName());
            printf("%-5s",course.getClassNum());
            printf("%-10s",course.getSemester());
            print("\n");
        }
    }

    public void showGradesInfoByCourse(List<Registration> targetRegistrationList) {
        Course targetCourse = targetRegistrationList.get(0).getCourse();
        print("\n< 조회 강의 : "+targetCourse.getSubject().getName() + " / 분반 : " + targetCourse.getClassNum() + " >\n");
        print("\n[학번]\t\t[이름]\t\t\t\t[성적] [재수강]\n");
        for(Registration registration : targetRegistrationList){
            String grade = registration.getGrade();
            if(grade.compareTo("B0") >0){
                printf("%-12s",registration.getStudent().getStudentNum()+"*");
            }else{
                printf("%-12s",registration.getStudent().getStudentNum());
            }
            printf("%-22s",registration.getStudent().getName());
            printf("%-5s",grade);
            printf("%-5s",registration.getIsRetake());
            print("\n");

        }
    }
    public void showGradesInfoByStudent(List<Registration> targetRegistrationList, Map<String, String> gradeInfo) {
        print("\n[강의번호]\t\t[과목번호]\t\t[강의명]\t\t\t\t\t\t[학점] [성적] [재수강]\n");
        for(Registration registration : targetRegistrationList){
            Course course = registration.getCourse();
            Subject subject = course.getSubject();

            printf("%-12s",course.getCourseNum());
            printf("%-12s",subject.getSubjectNum());
            printf("%-30s",subject.getName());
            printf("%-5s",subject.getCredit());
            printf("%-7s",registration.getGrade());
            printf("%-5s",registration.getIsRetake());
            print("\n");
        }
        print("----------------------------------------------------------------------------------------------\n");
        print("\n총 수강 학점 : "+gradeInfo.get("totalCredit") + "\t/\t평점 평균 : "+ gradeInfo.get("averageGrade")+" \n");
    }

    public String showProfessorNumCommand() {
        print("\n교수 직번을 입력하세요 >> ");
        return MyScanner.stringScanner();
    }

    public String showSchoolYearCommand() {
        print("조회할 학년을 입력하세요 >> ");
        return MyScanner.stringScanner();
    }

    public void showStudentGradeList(Map<String, String> gradeListMap) {
        print("\n---------- 성적순 조회 -----------\n");
        print("\n[학번]\t\t [성적]\n");
        for (Map.Entry<String, String> currentEntry : gradeListMap.entrySet()) {
           printf("%-13s",currentEntry.getKey());
           printf("%-5s",currentEntry.getValue());
           print("\n");
        }
        print("\n");

    }
}
