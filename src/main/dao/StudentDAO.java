package main.dao;

import main.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentDAO {
    private static final String STUDENT_FILE_PATH = "/Users/limjeonghyun/Desktop/ManageProgram/info_files/studentInfo";
    private static final String STUDENT_NUM_FILE_PATH = "/Users/limjeonghyun/Desktop/ManageProgram/info_files/studentNumber";

    File studentInfo = new File(STUDENT_FILE_PATH);
    File studentNumberInfo = new File(STUDENT_NUM_FILE_PATH);

    public List<Student> getAllStudentsInfo(){
        List<Student> studentList = new ArrayList<>();

        try {
            if(!studentInfo.exists()) return studentList;
            FileReader fileReader = new FileReader(studentInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                Student student = convertStringToStudent(line);
                studentList.add(student);
            }
            bufReader.close();
            fileReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public void writeAllStudentInfo(List<Student> students){

        try {
            FileWriter fileWriter = new FileWriter(STUDENT_FILE_PATH,false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Student student : students){
                bufferedWriter.write(student.toString());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Student convertStringToStudent(String line) {
        String[] studentInfo = line.split("/");
        Student student = new Student();
        student.setStudentNum(studentInfo[0]);
        student.setName(studentInfo[1]);
        student.setRRN(studentInfo[2]);
        student.setPhoneNum(studentInfo[3]);
        student.setEmail(studentInfo[4]);
        return student;
    }
    public boolean findStuent(String studentNum){
        String fountStudentNum = "";
        boolean isExist = false;

        if(!studentInfo.exists()) return isExist;
        try {
            FileReader fileReader = new FileReader(studentInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                fountStudentNum = line.split("/")[0];
                if (fountStudentNum.equals(studentNum)) {
                    isExist = true;
                    break;
                }
            }
            bufReader.close();
            fileReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return isExist;
    }

    public boolean checkDupleStudent(String studentRRN) {
        String fountStudentRRN = "";
        boolean isExist = false;

        if(!studentInfo.exists()) return isExist;
        try {
            FileReader fileReader = new FileReader(studentInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                fountStudentRRN = line.split("/")[2];
                if (fountStudentRRN.equals(studentRRN)) {
                    isExist = true;
                    break;
                }
            }
            bufReader.close();
            fileReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return isExist;
    }
    public boolean insertStudentInfo(Student newStudent){
        String newStudentInfo = newStudent.toString();
        try {
            FileWriter fileWriter = new FileWriter(STUDENT_FILE_PATH,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newStudentInfo);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean updateStudentInfo(Student targetStudent) {
        boolean isUpdated = false;
        List<Student> studentList = getAllStudentsInfo();
        for(Student student : studentList){
            if(student.getStudentNum().equals(targetStudent.getStudentNum())){
                student.setRRN(targetStudent.getRRN());
                student.setName(targetStudent.getName());
                student.setPhoneNum(targetStudent.getPhoneNum());
                student.setEmail(targetStudent.getEmail());
                isUpdated = true;
                break;
            }
        }
        writeAllStudentInfo(studentList);
        return isUpdated;
    }

    public boolean deleteStudentInfo(String studentNum) {
        boolean isDeleted = false;
        List<Student> studentList = getAllStudentsInfo();
        for(Student student : studentList){
            if(student.getStudentNum().equals(studentNum)){
                studentList.remove(student);
                isDeleted = true;
                break;
            }
        }
        writeAllStudentInfo(studentList);
        return isDeleted;
    }

    public String getNextStudentNum() {
        int nextStudentNum = 18000001;
        try {
            if(studentNumberInfo.exists()){
                FileReader fileReader = new FileReader(studentNumberInfo);
                BufferedReader bufReader = new BufferedReader(fileReader);

                String line = "";
                if((line = bufReader.readLine()) != null){
                    nextStudentNum = Integer.parseInt(line);
                }
                bufReader.close();
                fileReader.close();
            }
            FileWriter fileWriter = new FileWriter(STUDENT_NUM_FILE_PATH,false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(nextStudentNum + 1));
            bufferedWriter.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.toString(nextStudentNum);
    }

    public Student inquireStudentInfo(String studentNum) {
        Student selectedStudent = null;
        String currentStudentNum = "";
        try {
            if(!studentInfo.exists()) return selectedStudent;

            FileReader fileReader = new FileReader(studentInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                currentStudentNum = line.split("/")[0];
                if (currentStudentNum.equals(studentNum)) {
                    selectedStudent = convertStringToStudent(line);
                    break;
                }
            }
            bufReader.close();
            fileReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return selectedStudent;
    }

    public List<Student> inquireStudentsInfo(String targetStudentName) {
        String currentStudentName="";
        Student foundStudent;

        List<Student> selectedStudentList = new ArrayList<>();

        try {
            if(!studentInfo.exists()) return selectedStudentList;
            FileReader fileReader = new FileReader(studentInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                currentStudentName = line.split("/")[1];
                if (currentStudentName.equals(targetStudentName)) {
                    foundStudent = convertStringToStudent(line);
                    selectedStudentList.add(foundStudent);
                }
            }
            bufReader.close();
            fileReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return selectedStudentList;

    }
    private Comparator<Student> numComparator = new Comparator<Student>(){
        @Override
        public int compare(Student s1, Student s2) {
            return s1.getStudentNum().compareTo(s2.getStudentNum());
        }
    };
    private Comparator<Student> nameComparator = new Comparator<Student>(){
        @Override
        public int compare(Student s1, Student s2) {
            return s1.getName().compareTo(s2.getName());
        }
    };
    public List<Student> sortStudentsInfoByStudentNum() {
        List<Student> studentList = getAllStudentsInfo();
        Collections.sort(studentList,numComparator);
        return studentList;
    }

    public List<Student> sortStudentsInfoByName() {
        List<Student> studentList = getAllStudentsInfo();
        Collections.sort(studentList,nameComparator);
        return studentList;
    }


}
