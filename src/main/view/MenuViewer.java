package main.view;

import main.Message;
import main.MyPrinter;
import main.MyScanner;
import main.model.Admin;

public class MenuViewer extends MyPrinter {

    public String showMain() {

        print("\n######## ManagerSystem ########\n\n");
        print(" 1. 로그인 \n");
        print(" 2. 나가기 \n\n");
        print(" >> ");

        return MyScanner.stringScanner();
    }

    public Admin showLoginCommand() {

        Admin loginAdmin = new Admin();

        print("\n######## Login ########\n\n");
        print(" 아이디 >> ");
        loginAdmin.setId(MyScanner.stringScanner());

        print(" 비밀번호 >> ");
        loginAdmin.setPassword(MyScanner.stringScanner());

        return loginAdmin;
    }
    public String showMenu() {

        print("\n######## Menu ########\n\n");
        print(" 1. 학생 관리 \n");
        print(" 2. 교수 관리 \n");
        print(" 3. 강의 관리 \n");
        print(" 4. 수강 관리 \n");
        print(" 5. 성적 관리 \n");
        print(" -------------\n");
        print(" 0. 로그아웃 \n\n");
        print(" >> ");

        return MyScanner.stringScanner();
    }

    public void showLoginFailMessage() {
        print("\n"+Message.LOGIN_FAIL+"\n");
    }

    public void showInputError() {
        print("\n"+Message.INPUT_ERROR+"\n");
    }
}
