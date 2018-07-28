package main.view;

import main.Message;
import main.MyPrinter;
import main.MyScanner;
import main.model.Student;

import java.util.List;

public class StudentViewer extends MyPrinter {

    public String showMenu() {
       print("\n########### [1] 학생관리 ############\n");
       print("\n 1. 학생 정보 입력 \n");
       print(" 2. 학생 정보 조회\n");
       print(" 3. 학생 정보 수정 \n");
       print(" 4. 학생 정보 삭제 \n");
       print("------------------\n");
       print(" 0. 뒤로가기 \n\n");
       print(" >> ");
       return MyScanner.stringScanner();
    }
    public Student showStudentInfoInputCommand(){
        Student student = new Student();
        print("\n 새로 추가할 학생의 정보를 입력하세요.\n\n");
        print(" 이름 : ");
        student.setName(MyScanner.stringScanner());
        print(" 주민번호 : ");
        student.setRRN(MyScanner.stringScanner());
        print(" 전화번호 : ");
        student.setPhoneNum(MyScanner.stringScanner());
        print(" 이메일 : ");
        student.setEmail(MyScanner.stringScanner());
        return student;
    }

    public Student showModifyInfoCommand(Student student)
    {
        print(" 변경할 정보를 입력하세요. \n\n");

        print(" 이름 : ");
        String name = MyScanner.stringScanner();
        name = "".equals(name) ? student.getName() : name;
        student.setName(name);

        print(" 주민번호 : ");
        String rrn = MyScanner.stringScanner();
        rrn = "".equals(rrn) ? student.getRRN() : rrn;
        student.setRRN(rrn);

        print(" 전화번호 : ");
        String phoneNum = MyScanner.stringScanner();
        phoneNum = "".equals(phoneNum) ? student.getPhoneNum() : phoneNum;
        student.setPhoneNum(phoneNum);

        print(" 이메일 : ");
        String email = MyScanner.stringScanner();
        email = "".equals(email) ? student.getEmail(): email;
        student.setEmail(email);

        return student;
    }

    public String showStudentNumCommand() {
        print("\n학번 >> ");
        return MyScanner.stringScanner();
    }

    public void showStudentInfo(Student targetStudent) {
        print("\n < 조회 정보 > ");
        print("\n 학번 : " + targetStudent.getStudentNum()+"\n");
        print(" 이름 : " + targetStudent.getName()+"\n");
        print(" 주민번호 : " + targetStudent.getRRN()+"\n");
        print(" 전화번호 : " + targetStudent.getPhoneNum()+"\n");
        print(" 이메일 : " + targetStudent.getEmail()+"\n\n");
    }
    public void showNotExistError() {
        print("\n"+Message.NOT_EXIST_ERROR+"\n");
    }

    public void showSaveSuccessMessage() {
        print("\n"+Message.SAVE_SUCCESS + "a new student information\n");
    }

    public void showSaveFailMessage() {
        print("\n"+Message.SAVE_FAIL + "a new student information\n");
    }

    public void showModifySuccessMessage() {
        print("\n"+Message.MODIFY_SUCCESS + "the student information\n");
    }

    public void showModifyFailMessage() {
        print("\n"+Message.MODIFY_FAIL + "the student information\n");
    }

    public void showRemoveSuccessMessage() {
        print("\n"+Message.REMOVE_SUCCESS + "the student information\n");
    }

    public void showRemoveFailMessage() {
        print("\n"+Message.REMOVE_FAIL + "the student information\n");
    }

    public void showSuccessInquireMessage() {
        print(Message.INQUIRE_SUCCESS + "the student information\n");
    }
    public void showNoDataMessage(){
        print("\n"+Message.NO_DATA + "in student file\n");
    }
    public String showInquireMenu() {
        print("\n 1. 학번 검색 \n");
        print(" 2. 이름 검색\n");
        print(" 3. 학번순 정렬\n");
        print(" 4. 이름순 정렬\n");
        print("------------------\n");
        print(" 0. 뒤로가기 \n\n");
        print(" >> ");
        return MyScanner.stringScanner();
    }


    public String showStudentNameCommand() {
        print("\n 이름 >> ");
        return MyScanner.stringScanner();
    }

    public void showStudentsInfo(List<Student> targetStudents) {
        print("\n###################  조회된 학생 정보 ####################\n\n");
        print("[학번]\t\t[이름]\t\t\t[주민번호]\t\t\t[휴대폰번호]\t\t[이메일]\n");
        for(Student student : targetStudents){
            printf("%-12s",student.getStudentNum());
            printf("%-15s",student.getName());
            printf("%-17s",student.getRRN());
            printf("%-15s",student.getPhoneNum());
            printf("%-20s",student.getEmail());
            print("\n");
        }

    }

}
