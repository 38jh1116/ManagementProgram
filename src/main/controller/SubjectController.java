package main.controller;

import main.manager.SubjectManager;
import main.model.Subject;
import main.view.SubjectViewer;

import java.util.List;

public class SubjectController {
    private SubjectViewer subjectViewer;
    private SubjectManager subjectManager;

    public SubjectController(){
        subjectViewer = new SubjectViewer();
        subjectManager = new SubjectManager();

    }
    public void run() {
        boolean isBack = false;
        String selectedMenu = "";

        while(!isBack){
            selectedMenu =  subjectViewer.showMenu();
            switch (selectedMenu) {
                case "0":
                    isBack = true;
                    break;
                case "1":
                    saveSubjectInfo();
                    break;
                case "2":
                    inquireSubjectInfo();
                    break;
                case "3":
                    modifySubjectInfo();
                    break;
                case "4":
                    removeSubjectInfo();
                    break;
                default:
                    break;
            }
        }
    }
    private void saveSubjectInfo(){

        Subject newSubject = subjectViewer.showSubjectInfoInputCommand();

        if(subjectManager.saveSubjectInfo(newSubject)){
            subjectViewer.showSaveSuccessMessage();
        }else{
            subjectViewer.showSaveFailMessage();
        }
    }
    private void inquireSubjectInfo() {

        if(displayAllSubjectsInfo()) return;
        boolean isBack = false;
        while(!isBack){
            String inquireMenu = subjectViewer.showInquireMenu();
            switch (inquireMenu){
                case "1" :
                    searchBySubjectNumber();
                    break;
                case "2" :
                    searchBySubjectName();
                    break;
                case "3" :
                    sortBySubjectNumber();
                    break;
                case "4" :
                    sortBySubjectName();
                    break;
                case "0":
                    isBack = true;
                    break;
                default:
                    break;
            }
        }

    }
    private void modifySubjectInfo() {
        if(displayAllSubjectsInfo()) return;
        String subjectNum = subjectViewer.showSubjectNumCommand();
        Subject targetSubject = subjectManager.inquireSubjectInfo(subjectNum);

        if(targetSubject != null){
            subjectViewer.showSubjectInfo(targetSubject);
            targetSubject = subjectViewer.showModifyInfoCommand(targetSubject);
            if(subjectManager.modifySubjectInfo(targetSubject)){
                subjectViewer.showModifySuccessMessage();
            }
            else{
                subjectViewer.showModifyFailMessage();
            }
        }else{
            subjectViewer.showNotExistError();
        }

    }

    private void removeSubjectInfo() {
        if(displayAllSubjectsInfo()) return;

        String subjectNum = subjectViewer.showSubjectNumCommand();
        if (subjectManager.removeSubjectInfo(subjectNum)) {
            subjectViewer.showRemoveSuccessMessage();
        } else {
            subjectViewer.showRemoveFailMessage();
        }
    }

    private boolean displayAllSubjectsInfo(){
        boolean isEmpty = false;
        List<Subject> subjectList = subjectManager.getAllSubjectsInfo();
        if(subjectList.size() <= 0){
            subjectViewer.showNoDataMessage();
            isEmpty = true;
        }
        else {
            subjectViewer.showSubjectsInfo(subjectList);
        }
        return isEmpty;
    }

    private void searchBySubjectNumber() {
        String subjectNum = subjectViewer.showSubjectNumCommand();
        Subject targetSubject = subjectManager.inquireSubjectInfo(subjectNum);
        if(targetSubject != null){
            subjectViewer.showSuccessInquireMessage();
            subjectViewer.showSubjectInfo(targetSubject);
        }
        else{
            subjectViewer.showNotExistError();
        }
    }

    private void searchBySubjectName() {
        String subjectName = subjectViewer.showSubjectNameCommand();
        List<Subject> targetSubjects = subjectManager.inquireSubjectsInfoByName(subjectName);

        if(targetSubjects.size() > 0){
            subjectViewer.showSuccessInquireMessage();
            subjectViewer.showSubjectsInfo(targetSubjects);
        }
        else{
            subjectViewer.showNotExistError();
        }
    }

    private void sortBySubjectNumber() {
        List<Subject> sortedListBySubjectNumber = subjectManager.sortSubjectsInfoBySubjectNum();
        subjectViewer.showSubjectsInfo(sortedListBySubjectNumber);
    }
    private void sortBySubjectName() {
        List<Subject> sortedListBySubjectName = subjectManager.sortSubjectsInfoByName();
        subjectViewer.showSubjectsInfo(sortedListBySubjectName);
    }
}