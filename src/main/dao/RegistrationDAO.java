package main.dao;

import main.model.Registration;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RegistrationDAO {
    private static final String REGISTRATION_FILE_PATH = "/Users/limjeonghyun/Desktop/ManageProgram/info_files/registrationInfo";
    private static final String REGISTRATION_NUM_FILE_PATH = "/Users/limjeonghyun/Desktop/ManageProgram/info_files/registrationNumber";

    File registrationInfo = new File(REGISTRATION_FILE_PATH);
    File registrationNumberInfo = new File(REGISTRATION_NUM_FILE_PATH);

    public List<Registration> getAllRegistrationsInfo() {
        List<Registration> registrationList = new ArrayList<>();

        try {
            if (!registrationInfo.exists()) return registrationList;
            FileReader fileReader = new FileReader(registrationInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                Registration registration = convertStringToRegistration(line);
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
            FileWriter fileWriter = new FileWriter(REGISTRATION_FILE_PATH, false);
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
        registration.setRegistrationNum(registrationInfo[2]);
        return registration;
    }

    public boolean checkDupleRegistration(Registration targetRegistrationNum) {
        String targetRegistrationCourseNum = targetRegistrationNum.getCourseNum();
        String targetRegistrationStudentNum = targetRegistrationNum.getStudentNum();

        String currentRegistrationCourseNum = "";
        String currentRegistrationStudentNum = "";

        boolean isExist = false;

        if (!registrationInfo.exists()) return isExist;
        try {
            FileReader fileReader = new FileReader(registrationInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                currentRegistrationStudentNum = line.split("/")[1];
                currentRegistrationCourseNum = line.split("/")[2];
                if (currentRegistrationStudentNum.equals(targetRegistrationStudentNum)
                        && currentRegistrationCourseNum.equals(targetRegistrationCourseNum)) {
                    isExist = true;
                    break;
                }
            }
            bufReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isExist;
    }

    public boolean insertRegistrationInfo(Registration newRegistration) {
        String newRegistrationInfo = newRegistration.toString();
        try {
            FileWriter fileWriter = new FileWriter(REGISTRATION_FILE_PATH, true);
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
                registration.setStudentNum(targetRegistration.getStudentNum());
                registration.setCourseNum(targetRegistration.getCourseNum());
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
            FileWriter fileWriter = new FileWriter(REGISTRATION_NUM_FILE_PATH, false);
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
        String curRegistrationNum = "";
        try {
            if (!registrationInfo.exists()) return selectedRegistration;
            FileReader fileReader = new FileReader(registrationInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                curRegistrationNum = line.split("/")[0];
                if (curRegistrationNum.equals(registrationNum)) {
                    selectedRegistration = convertStringToRegistration(line);
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
        String currentRegistrationStudentNum = "";
        Registration selectedRegistration;

        List<Registration> selectedRegistrationList = new ArrayList<>();

        try {
            if (!registrationInfo.exists()) return selectedRegistrationList;
            FileReader fileReader = new FileReader(registrationInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                currentRegistrationStudentNum = line.split("/")[1];
                if (currentRegistrationStudentNum.equals(targetRegistrationStudentNum)) {
                    selectedRegistration = convertStringToRegistration(line);
                    selectedRegistrationList.add(selectedRegistration);
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
        String currentRegistrationCourseNum = "";
        Registration selectedRegistration;

        List<Registration> selectedRegistrationList = new ArrayList<>();

        try {
            if (!registrationInfo.exists()) return selectedRegistrationList;
            FileReader fileReader = new FileReader(registrationInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                currentRegistrationCourseNum = line.split("/")[2];
                if (currentRegistrationCourseNum.equals(targetRegistrationCourseNum)) {
                    selectedRegistration = convertStringToRegistration(line);
                    selectedRegistrationList.add(selectedRegistration);
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
            return r1.getCourseNum().compareTo(r2.getCourseNum());
        }
    };
    private Comparator<Registration> studentNumComparator = new Comparator<Registration>() {
        @Override
        public int compare(Registration r1, Registration r2) {
            return r1.getStudentNum().compareTo(r2.getStudentNum());
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
}



