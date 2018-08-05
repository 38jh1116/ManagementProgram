package main.dao;

import main.FilePath;
import main.Message;
import main.model.Course;
import main.model.Registration;
import main.model.Student;

import java.io.*;
import java.util.*;

public class RegistrationDAO {

    private StudentDAO studentDAO;
    private CourseDAO courseDAO;

    File registrationInfo = new File(FilePath.REGISTRATION_FILE_PATH);
    File registrationNumberInfo = new File(FilePath.REGISTRATION_NUM_FILE_PATH);


    public RegistrationDAO(){
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
    }
    public List<Registration> getAllRegistrationsInfo() {
        List<Registration> registrationList = new ArrayList<>();

        try {
            if (!registrationInfo.exists()) return registrationList;
            FileReader fileReader = new FileReader(registrationInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                Registration registration = convertStringToRegistration(line);
                registration = addStudentAndCourseInfo(registration);
                registrationList.add(registration);
            }
            bufReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registrationList;
    }

    public void writeAllRegistrationInfo(List<Registration> registrations) {

        try {
            FileWriter fileWriter = new FileWriter(FilePath.REGISTRATION_FILE_PATH, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Registration registration : registrations) {
                bufferedWriter.write(registration.toString());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Registration convertStringToRegistration(String line) {
        String[] registrationInfo = line.split("/");
        Registration registration = new Registration();
        registration.setRegistrationNum(registrationInfo[0]);
        registration.setStudentNum(registrationInfo[1]);
        registration.setCourseNum(registrationInfo[2]);
        registration.setGrade(registrationInfo[3]);
        registration.setIsRetake(registrationInfo[4]);
        return registration;
    }

    public String checkPossibility(Registration targetRegistration) {
        String targetRegistrationCourseNum = targetRegistration.getCourse().getCourseNum();
        String targetRegistrationSubjectNum = courseDAO.inquireCourseInfo(targetRegistrationCourseNum).getSubject().getSubjectNum();
        String targetRegistrationStudentNum = targetRegistration.getStudent().getStudentNum();

        List<Registration> targetStudentRegistrationList = inquireRegistrationsByStudentNum(targetRegistrationStudentNum);
        String takeCase = Message.POSSIBLE_AND_NEW;

        if (!registrationInfo.exists()) return takeCase;
        for(Registration registration :targetStudentRegistrationList){
            if(registration.getCourse().getSubject().getSubjectNum().equals(targetRegistrationSubjectNum)){
                takeCase = Message.POSSIBLE_AND_NEW;
                if(registration.getGrade().compareTo("C+") < 0) {
                    takeCase =  Message.IMPOSSIBLE;
                    break;
                }
            }
        }
        return takeCase;
    }

    public boolean insertRegistrationInfo(Registration newRegistration) {
        String newRegistrationInfo = newRegistration.toString();
        try {
            FileWriter fileWriter = new FileWriter(FilePath.REGISTRATION_FILE_PATH, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newRegistrationInfo);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean updateRegistrationInfo(Registration targetRegistration) {
        boolean isUpdated = false;
        List<Registration> registrationList = getAllRegistrationsInfo();
        for (Registration registration : registrationList) {
            if (registration.getRegistrationNum().equals(targetRegistration.getRegistrationNum())) {
                registration.setCourseNum(targetRegistration.getCourse().getCourseNum());
                registration.setGrade(targetRegistration.getGrade());
                isUpdated = true;
                break;
            }
        }
        writeAllRegistrationInfo(registrationList);
        return isUpdated;
    }

    public boolean deleteRegistrationInfo(String registrationNum) {
        boolean isDeleted = false;
        List<Registration> registrationList = getAllRegistrationsInfo();
        for (Registration registration : registrationList) {
            if (registration.getRegistrationNum().equals(registrationNum)) {
                registrationList.remove(registration);
                isDeleted = true;
                break;
            }
        }
        writeAllRegistrationInfo(registrationList);
        return isDeleted;
    }

    public String getNextRegistrationNum() {
        int nextRegistrationNum = 88000001;
        try {
            if (registrationNumberInfo.exists()) {
                FileReader fileReader = new FileReader(registrationNumberInfo);
                BufferedReader bufReader = new BufferedReader(fileReader);

                String line = "";
                if ((line = bufReader.readLine()) != null) {
                    nextRegistrationNum = Integer.parseInt(line);
                }
                bufReader.close();
                fileReader.close();
            }
            FileWriter fileWriter = new FileWriter(FilePath.REGISTRATION_NUM_FILE_PATH, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(nextRegistrationNum + 1));
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.toString(nextRegistrationNum);
    }

    public Registration inquireRegistrationInfo(String registrationNum) {
        Registration selectedRegistration = null;
        Registration currentRegistration;
        try {
            if (!registrationInfo.exists()) return selectedRegistration;
            FileReader fileReader = new FileReader(registrationInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                currentRegistration = convertStringToRegistration(line);
                if (currentRegistration.getRegistrationNum().equals(registrationNum)) {
                    selectedRegistration = currentRegistration;
                    break;
                }
            }
            bufReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return selectedRegistration;
    }

    public List<Registration> inquireRegistrationsByStudentNum(String targetRegistrationStudentNum) {
        Registration currentRegistration;

        List<Registration> selectedRegistrationList = new ArrayList<>();

        try {
            if (!registrationInfo.exists()) return selectedRegistrationList;
            FileReader fileReader = new FileReader(registrationInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                currentRegistration = convertStringToRegistration(line);
                if (currentRegistration.getStudent().getStudentNum().equals(targetRegistrationStudentNum)) {
                    currentRegistration = addStudentAndCourseInfo(currentRegistration);
                    selectedRegistrationList.add(currentRegistration);
                }
            }
            bufReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return selectedRegistrationList;
    }

    public List<Registration> inquireRegistrationsByCourseNum(String targetRegistrationCourseNum) {
        Registration currentRegistration;
        List<Registration> selectedRegistrationList = new ArrayList<>();

        try {
            if (!registrationInfo.exists()) return selectedRegistrationList;
            FileReader fileReader = new FileReader(registrationInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                currentRegistration = convertStringToRegistration(line);
                if (currentRegistration.getCourse().getCourseNum().equals(targetRegistrationCourseNum)) {
                    currentRegistration = addStudentAndCourseInfo(currentRegistration);
                    selectedRegistrationList.add(currentRegistration);
                }
            }
            bufReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return selectedRegistrationList;
    }

    private Comparator<Registration> numComparator = new Comparator<Registration>() {
        @Override
        public int compare(Registration r1, Registration r2) {
            return r1.getCourse().getCourseNum().compareTo(r2.getCourse().getCourseNum());
        }
    };
    private Comparator<Registration> studentNumComparator = new Comparator<Registration>() {
        @Override
        public int compare(Registration r1, Registration r2) {
            return r1.getStudent().getStudentNum().compareTo(r2.getStudent().getStudentNum());
        }
    };

    public List<Registration> sortRegistrationsInfoByCourse() {
        List<Registration> registrationList = getAllRegistrationsInfo();
        Collections.sort(registrationList, numComparator);
        return registrationList;
    }

    public List<Registration> sortRegistrationsInfoByStudent() {
        List<Registration> registrationList = getAllRegistrationsInfo();
        Collections.sort(registrationList, studentNumComparator);
        return registrationList;
    }

    public boolean checkStudent(String studentNum) {
        return studentDAO.findStudent(studentNum);
    }

    public boolean checkCourse(String courseNum) {
        return courseDAO.findCourse(courseNum);
    }

    private Registration addStudentAndCourseInfo(Registration registration){
        Student student = studentDAO.inquireStudentInfo(registration.getStudent().getStudentNum());
        Course course = courseDAO.inquireCourseInfo(registration.getCourse().getCourseNum());

        registration.setCourse(course);
        registration.setStudent(student);
        return registration;
    }

    public boolean saveGradeInfo(List<Registration> registrationList) {
        boolean isSaved = true;
        for(Registration targetRegistration : registrationList){
            if(!updateRegistrationInfo(targetRegistration)){
                isSaved = false;
                break;
            }
        }
        return isSaved;
    }
}



