package main.manager;

import main.dao.ProfessorDAO;
import main.model.Professor;

import java.util.List;

public class ProfessorManager {
    public ProfessorDAO professorDAO;

    public ProfessorManager(){
        professorDAO = new ProfessorDAO();
    }

    public boolean dupleCheck(String professorRRN){
        return professorDAO.checkDupleProfessork(professorRRN);
    }


    public boolean validationCheck(Professor professor){

        boolean isValid = true;
        if("".equals(professor.getName())
                || "".equals(professor.getRRN())
                || "".equals(professor.getPhoneNum())
                || "".equals(professor.getEmail())
                || "".equals(professor.getOfficeAddress())
                || "".equals(professor.getOfficeNum())) {
            isValid = false;
        }
        if(professor.getName().contains("/")
                || professor.getRRN().contains("/")
                || professor.getPhoneNum().contains("/")
                || professor.getEmail().contains("/")
                || professor.getOfficeAddress().contains("/")
                || professor.getOfficeNum().contains("/")) {
            isValid = false;
        }

        return isValid;
    }

    private String makeNewProfessorNum() {
        return professorDAO.getNextProfessorNum();
    }

    public boolean saveProfessorInfo(Professor newProfessor) {
        if(!validationCheck(newProfessor) || dupleCheck(newProfessor.RRN)){
            return false;
        }
        newProfessor.setProfessorNum(makeNewProfessorNum());
        return professorDAO.insertProfessorInfo(newProfessor);
    }
    public boolean modifyProfessorInfo(Professor targetProfessor) {

        if(!validationCheck(targetProfessor)){
            return false;
        }
        return professorDAO.updateProfessorInfo(targetProfessor);
    }

    public boolean removeProfessorInfo(String targetProfessorNum) {
        return professorDAO.deleteProfessorInfo(targetProfessorNum);
    }
    public Professor inquireProfessorInfo(String targetProfessorNum) {

        return professorDAO.inquireProfessorInfo(targetProfessorNum);
    }

    public List<Professor> inquireProfessorsInfo(String targetProfessorNum){
        return professorDAO.inquireProfessorsInfo(targetProfessorNum);
    }

    public List<Professor> sortProfessorsInfoByProfessorNum() {
        return professorDAO.sortProfessorsInfoByProfessorNum();
    }

    public List<Professor> sortProfessorInfoByName() {
        return professorDAO.sortProfessorInfoByName();
    }

    public List<Professor> getAllProfessorsInfo() {
        return professorDAO.getAllProfessorInfo();
    }

    public String getProfessorId(String targetProfessorName) {
        return professorDAO.getProfessorId(targetProfessorName);
    }
}
