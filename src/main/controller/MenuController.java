package main.controller;

import main.view.MenuViewer;

public class MenuController {
    private MenuViewer menuViewer;
    private StudentController studentController;
    private ProfessorController professorController;
    private CourseController courseController;
    private RegistrationController registrationController;
    private GradeController gradeController;
    private SubjectController subjectController;

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
                    subjectController = new SubjectController();
                    subjectController.run();
                    break;
                case "4":
                    courseController = new CourseController();
                    courseController.run();
                    break;
                    /*
                case "5":
                    registrationController = new RegistrationController();
                    registrationController.run();
                case "6":
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
