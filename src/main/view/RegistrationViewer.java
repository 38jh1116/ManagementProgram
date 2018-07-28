package main.view;

import main.Message;
import main.MyPrinter;
import main.MyScanner;
import main.model.Registration;

import java.util.List;

public class RegistrationViewer extends MyPrinter {

    public String showMenu() {
        print("\n########### [4] 수강 관리 ############\n");
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
        print("\n 새로 추가할 수강 정보를 입력하세요.\n\n");
        print(" 수강 학생 학번 : ");
        registration.setStudentNum(MyScanner.stringScanner());
        print(" 수강 강좌 번호 : ");
        registration.setCourseNum(MyScanner.stringScanner());
        return registration;
    }

    public Registration showModifyInfoCommand(Registration registration)
    {
        print(" 변경할 정보를 입력하세요. \n\n");

        print(" 수강 학생 학번 : ");
        String studentNum = MyScanner.stringScanner();
        studentNum = "".equals(studentNum) ? registration.getStudentNum() : studentNum;
        registration.setStudentNum(studentNum);

        print(" 수강 강좌 번호 : ");
        String courseNum = MyScanner.stringScanner();
        courseNum = "".equals(courseNum) ? registration.getCourseNum(): courseNum;
        registration.setCourseNum(courseNum);

        return registration;
    }

    public void showRegistrationInfo(Registration targetRegistration) {
        print("\n######## 조회 정보 ########### ");
        print("\n 수강 번호 : " + targetRegistration.getRegistrationNum() + "\n");
        print(" 수강 학생 학번 : " + targetRegistration.getStudentNum() + "\n");
        print(" 수강 강좌 번호 : " + targetRegistration.getCourseNum() + "\n\n");
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
        print("\n 1. 수강 학생별 검색 \n");
        print(" 2. 수강 강좌별 검색\n");
        print(" 3. 수강 학생별 정렬\n");
        print(" 4. 수강 강좌별 정렬\n");
        print("------------------\n");
        print(" 0. 뒤로가기 \n\n");
        print(" >> ");
        return MyScanner.stringScanner();
    }

    public void showRegistrationsInfo(List<Registration> targetRegistrations) {
        print("\n########################### 조회된 수강 정보 ##########################\n\n");
        print("[수강번호]\t\t[수강학생]\t\t[수강학수번호]\t  [강의명]\t\t\t [분반] [담당교수] \t[강의학기]\t\t  [강의시간]\t[학점]\n");
        for(Registration registration : targetRegistrations){
            printf("%-12s",registration.getRegistrationNum());
            printf("%-10s",registration.getStudentNum());
            printf("%-20s",registration.getCourseNum());
            print("\n");
        }
    }

    public String showRegistrationNumCommand(){
        print("\n 수강 번호 >> ");
        return MyScanner.stringScanner();
    }
    public String showStudentNumCommand() {
        print("\n 학번 >> ");
        return MyScanner.stringScanner();
    }

    public String showCourseNumCommand() {
        print("\n 학수 번호 >> ");
        return MyScanner.stringScanner();
    }
}
