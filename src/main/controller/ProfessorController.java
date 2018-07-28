package main.controller;

import main.manager.ProfessorManager;
import main.model.Professor;
import main.view.ProfessorViewer;

import java.util.List;

public class ProfessorController {
    private ProfessorViewer professorViewer;
    private ProfessorManager professorManager;

    public ProfessorController(){
        professorViewer = new ProfessorViewer();
        professorManager = new ProfessorManager();

    }
    public void run() {
        boolean isBack = false;
        String selectedMenu = "";

        while(!isBack){
            selectedMenu =  professorViewer.showMenu();
            switch (selectedMenu) {
                case "0":
                    isBack = true;
                    break;
                case "1":
                    saveProfessorInfo();
                    break;
                case "2":
                    inquireProfessorInfo();
                    break;
                case "3":
                    modifyProfessorInfo();
                    break;
                case "4":
                    removeProfessorInfo();
                    break;
                default:
                    break;
            }
        }
    }

    private void saveProfessorInfo(){

        Professor newProfessor = professorViewer.showProfessorInfoInputCommand();
        if(professorManager.saveProfessorInfo(newProfessor)){
            professorViewer.showSaveSuccessMessage();
        }else{
            professorViewer.showSaveFailMessage();
        }
    }
    private void inquireProfessorInfo() {

        displayAllProfessorsInfo();
        boolean isBack = false;
        while(!isBack){
            String inquireMenu = professorViewer.showInquireMenu();
            switch (inquireMenu){
                case "1" :
                    searchByProfessorNumber();
                    break;
                case "2" :
                    searchByProfessorName();
                    break;
                case "3" :
                    sortByProfessorNumber();
                    break;
                case "4" :
                    sortByProfessorName();
                    break;
                case "0":
                    isBack = true;
                    break;
                default:
                    break;
            }
        }

    }

    private void modifyProfessorInfo() {
        displayAllProfessorsInfo();
        String professorNum = professorViewer.showProfessorNumCommand();
        Professor targetProfessor = professorManager.inquireProfessorInfo(professorNum);

        if(targetProfessor != null){
            professorViewer.showProfessorInfo(targetProfessor);
            targetProfessor = professorViewer.showModifyInfoCommand(targetProfessor);
            if(professorManager.modifyProfessorInfo(targetProfessor)){
                professorViewer.showModifySuccessMessage();
            }
            else{
                professorViewer.showModifyFailMessage();
            }
        }else{
            professorViewer.showNotExistError();
        }

    }
    private void removeProfessorInfo() {
        displayAllProfessorsInfo();
        String professorNum = professorViewer.showProfessorNumCommand();

        if(professorManager.removeProfessorInfo(professorNum)) {
            professorViewer.showRemoveSuccessMessage();
        }else{
            professorViewer.showRemoveFailMessage();
        }
    }

    private void displayAllProfessorsInfo(){
        List<Professor> professorList = professorManager.getAllProfessorsInfo();
        if(professorList.size() <= 0){
            professorViewer.showNoDataMessage();
            return;
        }
        professorViewer.showProfessorsInfo(professorList);
    }

    private void searchByProfessorNumber() {
        String professorNum = professorViewer.showProfessorNumCommand();
        Professor targetProfessor = professorManager.inquireProfessorInfo(professorNum);
        if(targetProfessor != null){
            professorViewer.showSuccessInquireMessage();
            professorViewer.showProfessorInfo(targetProfessor);
        }
        else{
            professorViewer.showNotExistError();
        }
    }
    private void searchByProfessorName() {
        String professorName = professorViewer.showProfessorNameCommand();
        List<Professor> targetProfessors = professorManager.inquireProfessorsInfo(professorName);

        if(targetProfessors.size() > 0){
            professorViewer.showSuccessInquireMessage();
            professorViewer.showProfessorsInfo(targetProfessors);
        }
        else{
            professorViewer.showNotExistError();
        }
    }

    private void sortByProfessorNumber() {
        List<Professor> sortedListByProfessorNumber = professorManager.sortProfessorsInfoByProfessorNum();
        professorViewer.showProfessorsInfo(sortedListByProfessorNumber);
    }
    private void sortByProfessorName() {
        List<Professor> sortedListByProfessorName = professorManager.sortProfessorInfoByName();
        professorViewer.showProfessorsInfo(sortedListByProfessorName);
    }
}