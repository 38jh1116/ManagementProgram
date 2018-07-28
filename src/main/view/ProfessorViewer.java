package main.view;

import main.Message;
import main.MyPrinter;
import main.MyScanner;
import main.model.Professor;

import java.util.List;

public class ProfessorViewer extends MyPrinter {

    public String showMenu() {
        print("\n########### [2] 교수 관리 ############\n");
        print("\n 1. 교수 정보 입력 \n");
        print(" 2. 교수 정보 조회\n");
        print(" 3. 교수 정보 수정 \n");
        print(" 4. 교수 정보 삭제 \n");
        print("------------------\n");
        print(" 0. 뒤로가기 \n\n");
        print(" >> ");
        return MyScanner.stringScanner();
    }
    public Professor showProfessorInfoInputCommand(){
        Professor professor = new Professor();
        print("\n 새로 추가할 교수의 정보를 입력하세요.\n\n");
        print(" 이름 : ");
        professor.setName(MyScanner.stringScanner());
        print(" 주민번호 : ");
        professor.setRRN(MyScanner.stringScanner());
        print(" 전화번호 : ");
        professor.setPhoneNum(MyScanner.stringScanner());
        print(" 이메일 : ");
        professor.setEmail(MyScanner.stringScanner());
        print(" 연구실 위치 : ");
        professor.setOfficeAddress(MyScanner.stringScanner());
        print(" 연구실 번호 : ");
        professor.setOfficeNum(MyScanner.stringScanner());
        return professor;
    }

    public Professor showModifyInfoCommand(Professor professor)
    {
        print(" 변경할 정보를 입력하세요. (변경사항이 없을 경우 : Enter)\n\n");

        print(" 이름 : ");
        String name = MyScanner.stringScanner();
        name = "".equals(name) ? professor.getName() : name;
        professor.setName(name);

        print(" 주민번호 : ");
        String rrn = MyScanner.stringScanner();
        rrn = "".equals(rrn) ? professor.getRRN() : rrn;
        professor.setRRN(rrn);

        print(" 전화번호 : ");
        String phoneNum = MyScanner.stringScanner();
        phoneNum = "".equals(phoneNum) ? professor.getPhoneNum() : phoneNum;
        professor.setPhoneNum(phoneNum);

        print(" 이메일 : ");
        String email = MyScanner.stringScanner();
        email = "".equals(email) ? professor.getEmail() : email;
        professor.setEmail(email);

        print(" 연구실 위치 : ");
        String officeAddress = MyScanner.stringScanner();
        officeAddress = "".equals(officeAddress) ? professor.getOfficeAddress() : officeAddress;
        professor.setOfficeAddress(officeAddress);

        print(" 연구실 번호 : ");
        String officeNum = MyScanner.stringScanner();
        officeNum = "".equals(officeNum) ? professor.getOfficeNum() : officeNum;
        professor.setOfficeNum(officeNum);

        return professor;
    }

    public String showProfessorNumCommand() {
        print("\n직번 >> ");
        return MyScanner.stringScanner();
    }

    public void showProfessorInfo(Professor targetProfessor) {
        print("\n######## 조회 정보 ########### ");
        print("\n 직번 : " + targetProfessor.getProfessorNum()+"\n");
        print(" 이름 : " + targetProfessor.getName()+"\n");
        print(" 주민번호 : " + targetProfessor.getRRN()+"\n");
        print(" 전화번호 : " + targetProfessor.getPhoneNum()+"\n");
        print(" 이메일 : " + targetProfessor.getEmail()+"\n");
        print(" 연구실 위치 : " + targetProfessor.getOfficeAddress()+"\n");
        print(" 연구실 번호 : " + targetProfessor.getOfficeNum()+"\n\n");
    }
    public void showNotExistError() {
        print("\n"+Message.NOT_EXIST_ERROR+"\n");
    }

    public void showSaveSuccessMessage() {
        print("\n"+Message.SAVE_SUCCESS + "a new professor information\n");
    }

    public void showSaveFailMessage() {
        print("\n"+Message.SAVE_FAIL + "a new professor information\n");
    }

    public void showModifySuccessMessage() {
        print("\n"+Message.MODIFY_SUCCESS + "the professor information\n");
    }

    public void showModifyFailMessage() {
        print("\n"+Message.MODIFY_FAIL + "the professor information\n");
    }

    public void showRemoveSuccessMessage() {
        print("\n"+Message.REMOVE_SUCCESS + "the professor information\n");
    }

    public void showRemoveFailMessage() {
        print("\n"+Message.REMOVE_FAIL + "the professor information\n");
    }

    public void showSuccessInquireMessage() {
        print("\n"+Message.INQUIRE_SUCCESS + "the professor information\n");
    }

    public void showNoDataMessage(){
        print("\n"+Message.NO_DATA + "in professor file\n");
    }

    public String showInquireMenu() {
        print("\n 1. 직번 검색 \n");
        print(" 2. 이름 검색\n");
        print(" 3. 직번순 정렬\n");
        print(" 4. 이름순 정렬\n");
        print("------------------\n");
        print(" 0. 뒤로가기 \n\n");
        print(" >> ");
        return MyScanner.stringScanner();
    }


    public String showProfessorNameCommand() {
        print("\n 이름 >> ");
        return MyScanner.stringScanner();
    }

    public void showProfessorsInfo(List<Professor> targetProfessors) {
        print("\n######## 조회된 교수 정보 ########\n\n");
        print("[직번]\t\t[이름]\t\t\t[주민번호]\t\t\t[휴대폰번호]\t\t[이메일]\t\t\t\t\t[연구실위치]\t\t\t[연구실번호]\n");
        for(Professor professor : targetProfessors){
            printf("%-12s",professor.getProfessorNum());
            printf("%-15s",professor.getName());
            printf("%-17s",professor.getRRN());
            printf("%-15s",professor.getPhoneNum());
            printf("%-25s",professor.getEmail());
            printf("%-20s",professor.getOfficeAddress());
            printf("%-20s",professor.getOfficeNum());
            print("\n");
        }
    }

}
