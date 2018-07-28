package main;

import main.controller.MenuController;
import main.manager.LoginManager;
import main.model.Admin;
import main.view.MenuViewer;


public class App {

    MenuViewer menuViewer;
    MenuController menuController;

    boolean isExit;
    String selectedAppMenu;

    public App(){
        menuViewer = new MenuViewer();
        isExit = true;
    }

    public void run(){
        while(isExit){
            selectedAppMenu =  menuViewer.showMain();
            switch (selectedAppMenu){
                case "1" :
                    if(adminLogin()){
                        menuController = new MenuController();
                        menuController.run();
                    }
                    else menuViewer.showLoginFailMessage();
                    break;
                case "2" :
                    isExit = false;
                    break;
                default:
                    menuViewer.showInputError();
                    break;
            }
        }
    }

    private boolean adminLogin() {

        Admin adminInfo = menuViewer.showLoginCommand();
        LoginManager loginManager = new LoginManager();
        return loginManager.loginCheck(adminInfo);
    }

    public static void main(String[] args){
        App app = new App();
        app.run();
    }
}
