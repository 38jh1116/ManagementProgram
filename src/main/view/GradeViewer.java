package main.view;

import main.MyPrinter;

public class GradeViewer extends MyPrinter{
    public void showMenu() {
        print("\n############## Grade Manage ############\n");
        print(" 1. 성적 입력 \n");
        print(" 2. 성적 조회  \n");
        print("-------------------\n");
        print(" 0. 뒤로 가기 \n\n");
        print(">> ");
    }

    public void showEnterGradeMenu(){
        print("\n ########## 성적 입력 메뉴 ############\n");
        print(" 1. 학생별 입력\n");
        print(" 2. 수업별 입력\n");
        print("---------------\n");
        print(" 0. 뒤로 가기\n\n");
        print(">> ");
    }

    public void showCheckGradeMenu(){
        print("\n############# 성적 조회 메뉴 ############\n");
        print(" 1. 학생별 조회 \n");
        print(" 2. 수업별 조회 \n");
        print(" 3. 학기별 조회 \n");
        print("------------------\n");
        print(" 0. 뒤로 가기\n\n");
        print(">> ");
    }
    public void showStudentNumCommand(){
        print(" 학번 >> ");
    }
    public void showCourseNumCommand(){
        print(" 학수 번호 >> ");
    }
}
