package main.controller;

import main.view.MenuViewer;

public class MenuController {
    MenuViewer menuViewer;
    StudentController studentController;
    ProfessorController professorController;
    CourseController courseController;
    RegistrationController registrationController;
    GradeController gradeController;


    public MenuController(){
        menuViewer = new MenuViewer();
    }
    public void run(){
        boolean isLogout = false;
        String selectedMenu = "";

        while(!isLogout){
            selectedMenu =  menuViewer.showMenu();
            switch (selectedMenu) {
                case "0":
                    isLogout = true;
                    break;
                case "1":
                    studentController = new StudentController();
                    studentController.run();
                    break;
                case "2":
                    professorController = new ProfessorController();
                    professorController.run();
                    break;

                case "3":
                    courseController = new CourseController();
                    courseController.run();
                    break;
                    /*
                case "4":
                    registrationController = new RegistrationController();
                    registrationController.run();
                case "5":
                    gradeController = new GradeController();
                    gradeController.run();
                    break;
                    */
                default:
                    menuViewer.showInputError();
                    break;
            }
        }
    }
}
