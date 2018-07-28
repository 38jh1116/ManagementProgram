package main.manager;

import main.dao.SubjectDAO;
import main.model.Subject;
import java.util.List;

public class SubjectManager {
    private SubjectDAO subjectDAO;

    public SubjectManager(){
        subjectDAO = new SubjectDAO();
    }

    private boolean dupleCheck(String subjectName){
        return subjectDAO.checkDupleSubject(subjectName);
    }

    private boolean validationCheck(Subject subject){

        boolean isValid = true;
        if("".equals(subject.getName()) || "".equals(subject.getCredit())) {
            isValid = false;
        }
        if(subject.getName().contains("/") || subject.getCredit().contains("/")) {
            isValid = false;
        }

        return isValid;
    }

    private String makeNewSubjectNum() {
        return subjectDAO.getNextSubjectNum();
    }

    public boolean saveSubjectInfo(Subject newSubject) {
        if(!validationCheck(newSubject) || dupleCheck(newSubject.getName())){
            return false;
        }
        newSubject.setSubjectNum(makeNewSubjectNum());
        return subjectDAO.insertSubjectInfo(newSubject);
    }
    public boolean modifySubjectInfo(Subject targetSubject) {

        if(!validationCheck(targetSubject)){
            return false;
        }
        return subjectDAO.updateSubjectInfo(targetSubject);
    }

    public boolean removeSubjectInfo(String targetSubjectNum) {
        return subjectDAO.deleteSubjectInfo(targetSubjectNum);
    }
    public Subject inquireSubjectInfo(String targetSubjectNum) {

        return subjectDAO.inquireSubjectInfo(targetSubjectNum);
    }

    public List<Subject> inquireSubjectsInfoByName(String targetSubjectName){
        return subjectDAO.inquireSubjectsInfoByName(targetSubjectName);
    }

    public List<Subject> sortSubjectsInfoBySubjectNum() {
        return subjectDAO.sortSubjectsInfoBySubjectNum();
    }

    public List<Subject> sortSubjectsInfoByName() {
        return subjectDAO.sortSubjectsInfoByName();
    }

    public List<Subject> getAllSubjectsInfo() {
        return subjectDAO.getAllSubjectsInfo();
    }
}
