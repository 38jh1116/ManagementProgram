package main.view;

import main.Message;
import main.MyPrinter;
import main.MyScanner;
import main.model.Subject;

import java.util.List;

public class SubjectViewer extends MyPrinter {

    public String showMenu() {
        print("\n########### [3] 과목 관리 ############\n");
        print("\n 1. 과목 정보 입력 \n");
        print(" 2. 과목 정보 조회\n");
        print(" 3. 과목 정보 수정 \n");
        print(" 4. 과목 정보 삭제 \n");
        print("------------------\n");
        print(" 0. 뒤로가기 \n\n");
        print(" >> ");
        return MyScanner.stringScanner();
    }
    public Subject showSubjectInfoInputCommand(){
        Subject subject = new Subject();
        print("\n 새로 추가할 과목의 정보를 입력하세요.\n\n");

        print(" 과목 이름 : ");
        subject.setName(MyScanner.stringScanner());
        print(" 학점 : ");
        subject.setCredit(MyScanner.stringScanner());
        return subject;
    }

    public Subject showModifyInfoCommand(Subject subject)
    {
        print(" 변경할 정보를 입력하세요.(변경사항이 없을 경우 : Enter) \n\n");

        print(" 과목 이름 : ");
        String name = MyScanner.stringScanner();
        name = "".equals(name) ? subject.getName() : name;
        subject.setName(name);

        print(" 학점 : ");
        String credit = MyScanner.stringScanner();
        credit = "".equals(credit) ? subject.getCredit() : credit;
        subject.setCredit(credit);

        return subject;
    }

    public void showNotExistError() {
        print("\n"+Message.NOT_EXIST_ERROR+"\n");
    }

    public void showSaveSuccessMessage() {
        print("\n"+Message.SAVE_SUCCESS + "a new subject information\n");
    }

    public void showSaveFailMessage() {
        print("\n"+Message.SAVE_FAIL + "a new subject information\n");
    }

    public void showModifySuccessMessage() {
        print("\n"+Message.MODIFY_SUCCESS + "the subject information\n");
    }

    public void showModifyFailMessage() {
        print("\n"+Message.MODIFY_FAIL + "the subject information\n");
    }

    public void showRemoveSuccessMessage() {
        print("\n"+Message.REMOVE_SUCCESS + "the subject information\n");
    }

    public void showRemoveFailMessage() {
        print("\n"+Message.REMOVE_FAIL + "the subject information\n");
    }

    public void showSuccessInquireMessage() {
        print(Message.INQUIRE_SUCCESS + "the subject information\n");
    }
    public void showNoDataMessage(){
        print("\n"+Message.NO_DATA + "in subject file\n");
    }
    public String showInquireMenu() {
        print("\n 1. 과목 번호 검색 \n");
        print(" 2. 과목 이름 검색\n");
        print(" 3. 학수 번호순 정렬\n");
        print(" 4. 이름순 정렬\n");
        print("------------------\n");
        print(" 0. 뒤로가기 \n\n");
        print(" >> ");
        return MyScanner.stringScanner();
    }


    public String showSubjectNameCommand() {
        print("\n 과목 이름 >> ");
        return MyScanner.stringScanner();
    }

    public String showSubjectNumCommand() {
        print("\n과목 번호 >> ");
        return MyScanner.stringScanner();
    }
    public void showSubjectInfo(Subject targetSubject) {
        print("\n < 조회 정보 > ");
        print("\n 과목 번호 : " + targetSubject.getSubjectNum()+"\n");
        print(" 과목 이름 : " + targetSubject.getName()+"\n");
        print(" 학점 : " + targetSubject.getCredit()+"\n\n");

    }
    public void showSubjectsInfo(List<Subject> targetSubjects) {
        print("\n###################  조회된 과목 정보 ####################\n\n");
        print("[과목]\t\t[과목이름]\t\t\t\t\t\t[학점]\n");
        for(Subject subject : targetSubjects){
            printf("%-12s",subject.getSubjectNum());
            printf("%-30s",subject.getName());
            printf("%-5s",subject.getCredit());
            print("\n");
        }

    }

}
