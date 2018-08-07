package main.view;

import main.Message;
import main.MyPrinter;
import main.MyScanner;
import main.model.Course;
import main.model.Registration;
import main.model.Student;

import java.util.List;

public class RegistrationViewer extends MyPrinter {

    public String showMenu() {
        print("\n########### [5] 수강 관리 ############\n");
        print("\n 1. 수강 정보 입력 \n");
        print(" 2. 수강 정보 조회\n");
        print(" 3. 수강 정보 수정 \n");
        print(" 4. 수강 정보 삭제 \n");
        print("------------------\n");
        print(" 0. 뒤로가기 \n\n");
        print(" >> ");
        return MyScanner.stringScanner();
    }

    public Registration showRegistrationInfoInputCommand(){
        Registration registration = new Registration();
        print("\n 수강 정보를 추가할 학번을 입력하세요.\n\n");
        print(" 학번 : ");
        registration.setStudentNum(MyScanner.stringScanner());
        return registration;

    }

    public Registration showModifyInfoCommand(Registration registration)
    {
        print("\n새로 변경할 강의 번호를 입력하세요.(변경사항이 없을 경우 : Enter) \n\n");

        print(" 강의 번호 : ");
        String courseNum = MyScanner.stringScanner();
        courseNum = "".equals(courseNum) ? registration.getCourse().getCourseNum(): courseNum;
        registration.setCourseNum(courseNum);

        return registration;
    }

    public void showRegistrationInfo(Registration targetRegistration) {
        Course targetRegistrationCourse = targetRegistration.getCourse();
        Student targetRegistrationStudent = targetRegistration.getStudent();

        print("\n######## 조회 정보 ########### ");
        print("\n 수강 번호 : " + targetRegistration.getRegistrationNum() + "\n");
        print(" 수강 학생 학번 : " + targetRegistrationStudent.getStudentNum() + "\n");
        print(" 수강 학생 이름 : " + targetRegistrationStudent.getName() + "\n");
        print(" 수강 강좌 번호 : " + targetRegistrationCourse.getCourseNum() + "\n");
        print(" 수강 강좌 이름 : " + targetRegistrationCourse.getSubject().getSubjectNum() + "\n");
        print(" 수강 강좌 분반 : " + targetRegistrationCourse.getClassNum() + "\n");
        print(" 수강 강좌 담당 교수 : " + targetRegistrationCourse.getProfessor().getName() + "\n");
        print(" 수강 강좌 학기 : " + targetRegistrationCourse.getSemester() + "\n");
        print(" 수강 강좌 시간 : " + targetRegistrationCourse.getTime() + "\n");
        print(" 수강 강좌 학점 : " + targetRegistrationCourse.getSubject().getCredit() + "\n");
        print(" 수강 강좌 성적 : " + targetRegistration.getGrade()+"\n");
        print(" 수강 강좌 재수강여부 : " + targetRegistration.getIsRetake()+"\n\n");
    }

    public void showNotExistError(){
        print("\n"+Message.NOT_EXIST_ERROR+"\n");
    }

    public void showSaveSuccessMessage() {
        print("\n"+Message.SAVE_SUCCESS + "a new registration information\n");
    }

    public void showSaveFailMessage() {
        print("\n"+Message.SAVE_FAIL + "a new registration information\n");
    }

    public void showModifySuccessMessage() {
        print("\n"+Message.MODIFY_SUCCESS + "the registration information\n");
    }

    public void showModifyFailMessage() {
        print("\n"+Message.MODIFY_FAIL + "the registration information\n");
    }

    public void showRemoveSuccessMessage() {
        print("\n"+Message.REMOVE_SUCCESS + "the registration information\n");
    }

    public void showRemoveFailMessage() {
        print("\n"+Message.REMOVE_FAIL + "the registration information\n");
    }

    public void showSuccessInquireMessage() {
        print("\n"+Message.INQUIRE_SUCCESS + "the registration information\n");
    }

    public void showNoDataMessage(){
        print("\n"+Message.NO_DATA + "in registration file\n");
    }

    public String showInquireMenu() {
        print("\n 1. 수강 학생별 조회 \n");
        print(" 2. 수강 강좌별 조회\n");
        print("------------------\n");
        print(" 0. 뒤로가기 \n\n");
        print(" >> ");
        return MyScanner.stringScanner();
    }

    public void showRegistrationsInfo(List<Registration> targetRegistrations) {

        print("\n############################################################### 조회된 수강 정보 ####################################################\n\n");
        print("[번호]  [수강학생]\t\t\t\t\t\t[과목번호]\t\t\t[과목명]\t\t\t\t[분반]\t담당교수]\t\t\t\t[강의학기]\t\t[강의시간]\t\t [학점]\n");
        int index=1;
        for(Registration registration : targetRegistrations){
            Course currentCourse = registration.getCourse();
            Student currentStudent = registration.getStudent();
            printf("%-7s",index);

            printf("%-30s",currentStudent.getName()+"("+currentStudent.getStudentNum()+")");
            printf("%-15s",currentCourse.getCourseNum());
            printf("%-21s",currentCourse.getSubject().getName());
            printf("%-8s",currentCourse.getClassNum());
            printf("%-20s",currentCourse.getProfessor().getName());
            printf("%-10s",currentCourse.getSemester());
            printf("%-17s",currentCourse.getTime());
            printf("%-5s",currentCourse.getSubject().getCredit());
            print("\n");
            index++;
        }
        print("---------------------------------------------------------------------------------------------------------------------------------------------------\n");
    }

    public String showStudentNumCommand() {
        print("\n 수강 학생 학번 >> ");
        return MyScanner.stringScanner();
    }

    public String showCourseNumCommand() {
        print("\n 수강 강의 번호 >> ");
        return MyScanner.stringScanner();
    }

    public int showSelectNumCommand() {
        print("\n번호 >> ");
        return MyScanner.intScanner();
    }

    public void showInputValueError() {
        print("\n" + Message.INPUT_ERROR+"\n");
    }

    public void showAllCourseInfo(List<Course> courseList) {
        print("\n########################################### 전체 강의 목록  ###############################################\n\n");
        print("[강의번호]\t\t[과목번호]\t  [강의명]\t\t\t [분반] [담당교수]\t\t[담당교수직번]\t[강의학기]\t  [강의시간]\t\t[학점]\n");
        for(Course course : courseList){
            printf("%-12s",course.getCourseNum());
            printf("%-10s",course.getSubject().getSubjectNum());
            printf("%-20s",course.getSubject().getName());
            printf("%-5s",course.getClassNum());
            printf("%-15s",course.getProfessor().getName());
            printf("%-10s",course.getProfessor().getProfessorNum());
            printf("%-10s",course.getSemester());
            printf("%-17s",course.getTime());
            printf("%-5s",course.getSubject().getCredit());
            print("\n");
        }
        print("----------------------------------------------------------------------------------------------------------\n");
    }
}
