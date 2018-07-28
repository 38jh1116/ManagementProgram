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

        if(registrationManager.saveRegistrationInfo(newRegistration)){
            registrationViewer.showSaveSuccessMessage();
        }else{
            registrationViewer.showSaveFailMessage();
        }
    }

    private void inquireRegistrationInfo() {

        displayAllRegistrationsInfo();
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
                case "3" :
                    sortByStudentNum();
                    break;
                case "4" :
                    sortByCourseNum();
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

        displayAllRegistrationsInfo();
        String registrationNum = registrationViewer.showRegistrationNumCommand();
        Registration targetRegistration = registrationManager.inquireRegistrationInfo(registrationNum);

        if(targetRegistration != null){
            registrationViewer.showRegistrationInfo(targetRegistration);
            targetRegistration = registrationViewer.showModifyInfoCommand(targetRegistration);
            if(registrationManager.modifyRegistrationInfo(targetRegistration)){
                registrationViewer.showModifySuccessMessage();
            }
            else{
                registrationViewer.showModifyFailMessage();
            }
        }else{
            registrationViewer.showNotExistError();
        }

    }
    private void removeRegistrationInfo() {
        displayAllRegistrationsInfo();
        String registrationNum = registrationViewer.showRegistrationNumCommand();
        if(registrationManager.removeRegistrationInfo(registrationNum)) {
            registrationViewer.showRemoveSuccessMessage();
        } else {
            registrationViewer.showRemoveFailMessage();
        }
    }

    private void displayAllRegistrationsInfo(){
        List<Registration> registrationList = registrationManager.getAllRegistrationsInfo();
        if(registrationList.size() <= 0){
            registrationViewer.showNoDataMessage();
            return;
        }
        registrationViewer.showRegistrationsInfo(registrationList);

    }
    private void searchByStudentNum() {
        String studentNum = registrationViewer.showStudentNumCommand();

        List<Registration> targetRegistrations = registrationManager.inquireRegistrationsByStudentNum(studentNum);

        if(targetRegistrations.size() > 0){
            registrationViewer.showSuccessInquireMessage();
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
            registrationViewer.showSuccessInquireMessage();
            registrationViewer.showRegistrationsInfo(targetRegistrations);
        }
        else{
            registrationViewer.showNotExistError();
        }
    }

    private void sortByStudentNum() {
        List<Registration> sortedListByStudentNum = registrationManager.sortRegistrationsByStudentNum();
        registrationViewer.showRegistrationsInfo(sortedListByStudentNum);
    }
    private void sortByCourseNum() {
        List<Registration> sortedListByCourseNum = registrationManager.sortRegistrationsByCourseNum();
        registrationViewer.showRegistrationsInfo(sortedListByCourseNum);
    }


}