package main.controller;

import main.manager.RegistrationManager;
import main.model.Registration;
import main.view.RegistrationViewer;

import java.util.List;

public class RegistrationController {
    private RegistrationViewer registrationViewer;
    private RegistrationManager registrationManager;

    public RegistrationController(){
        registrationViewer = new RegistrationViewer();
        registrationManager = new RegistrationManager();

    }
    public void run() {
        boolean isBack = false;
        String selectedMenu = "";

        while(!isBack){
            selectedMenu =  registrationViewer.showMenu();
            switch (selectedMenu) {
                case "0":
                    isBack = true;
                    break;
                case "1":
                    saveRegistrationInfo();
                    break;
                case "2":
                    inquireRegistrationInfo();
                    break;
                case "3":
                    modifyRegistrationInfo();
                    break;
                case "4":
                    removeRegistrationInfo();
                    break;
                default:
                    break;
            }
        }
    }

    private void saveRegistrationInfo(){

        Registration newRegistration = registrationViewer.showRegistrationInfoInputCommand();
        registrationViewer.showAllCourseInfo(registrationManager.getAllCourseInfo());
        newRegistration.setCourseNum(registrationViewer.showCourseNumCommand());
        if(registrationManager.saveRegistrationInfo(newRegistration)){
            registrationViewer.showSaveSuccessMessage();
        }else{
            registrationViewer.showSaveFailMessage();
        }
    }

    private void inquireRegistrationInfo() {

        boolean isBack = false;
        while(!isBack){
            String inquireMenu = registrationViewer.showInquireMenu();
            switch (inquireMenu){
                case "1" :
                    searchByStudentNum();
                    break;
                case "2" :
                    searchByCourseNum();
                    break;
                case "0":
                    isBack = true;
                    break;
                default:
                    break;
            }
        }

    }
    private void modifyRegistrationInfo() {

        String studentNum = registrationViewer.showStudentNumCommand();
        List<Registration> targetRegistrationList = registrationManager.inquireRegistrationsByStudentNum(studentNum);
        Registration targetRegistration;

        if(targetRegistrationList.size() > 0) {
            registrationViewer.showRegistrationsInfo(targetRegistrationList);
            int targetIndex = registrationViewer.showSelectNumCommand();
            if(targetIndex < 1 || targetIndex > targetRegistrationList.size()) {
                registrationViewer.showInputValueError();
                return;
            }
            targetRegistration = targetRegistrationList.get(targetIndex);
            registrationViewer.showAllCourseInfo(registrationManager.getAllCourseInfo());
            targetRegistration = registrationViewer.showModifyInfoCommand(targetRegistration);

            if(registrationManager.modifyRegistrationInfo(targetRegistration)){
                registrationViewer.showModifySuccessMessage();
            }
            else{
                registrationViewer.showModifyFailMessage();
            }
        }else {
            registrationViewer.showNoDataMessage();
        }

    }
    private void removeRegistrationInfo() {
        String studentNum = registrationViewer.showStudentNumCommand();
        List<Registration> targetRegistrationList = registrationManager.inquireRegistrationsByStudentNum(studentNum);
        Registration targetRegistration;

        if(targetRegistrationList.size() > 0) {
            registrationViewer.showRegistrationsInfo(targetRegistrationList);
            int targetIndex = registrationViewer.showSelectNumCommand();
            if(targetIndex < 1 || targetIndex > targetRegistrationList.size()) {
                registrationViewer.showInputValueError();
                return;
            }
            targetRegistration = targetRegistrationList.get(targetIndex);
            if(registrationManager.removeRegistrationInfo(targetRegistration.getRegistrationNum())){
                registrationViewer.showRemoveSuccessMessage();
            }
            else{
                registrationViewer.showRemoveFailMessage();
            }
        }else {
            registrationViewer.showNoDataMessage();
        }

    }

    private void searchByStudentNum() {
        String studentNum = registrationViewer.showStudentNumCommand();

        List<Registration> targetRegistrations = registrationManager.inquireRegistrationsByStudentNum(studentNum);

        if(targetRegistrations.size() > 0){
            registrationViewer.showRegistrationsInfo(targetRegistrations);
        }
        else{
            registrationViewer.showNotExistError();
        }
    }

    private void searchByCourseNum() {
        String courseNum = registrationViewer.showCourseNumCommand();
        List<Registration> targetRegistrations = registrationManager.inquireRegistrationsByCourseNum(courseNum);

        if(targetRegistrations.size() > 0){
            registrationViewer.showRegistrationsInfo(targetRegistrations);
        }
        else{
            registrationViewer.showNotExistError();
        }
    }

}