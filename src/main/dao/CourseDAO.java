package main.dao;

import main.manager.ProfessorManager;
import main.model.Course;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CourseDAO {
    private static final String COURSE_FILE_PATH = "/Users/limjeonghyun/Desktop/ManageProgram/info_files/courseInfo";
    private static final String COURSE_NUM_FILE_PATH = "/Users/limjeonghyun/Desktop/ManageProgram/info_files/courseNumber";

    File courseInfo = new File(COURSE_FILE_PATH);
    File courseNumberInfo = new File(COURSE_NUM_FILE_PATH);

    public List<Course> getAllCoursesInfo(){
        List<Course> courseList = new ArrayList<>();

        try {
            if(!courseInfo.exists()) return courseList;
            FileReader fileReader = new FileReader(courseInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                Course course = convertStringToCourse(line);
                courseList.add(course);
            }
            bufReader.close();
            fileReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public void writeAllCourseInfo(List<Course> courses){

        try {
            FileWriter fileWriter = new FileWriter(COURSE_FILE_PATH,false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Course course : courses){
                bufferedWriter.write(course.toString());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Course convertStringToCourse(String line) {
        String[] courseInfo = line.split("/");
        Course course = new Course();
        course.setCourseNum(courseInfo[0]);
        course.setSubjectNum(courseInfo[1]);
        course.setCourseName(courseInfo[2]);
        course.setClassNum(courseInfo[3]);
        course.setProfessorId(courseInfo[4]);
        course.setSemester(courseInfo[5]);
        course.setTime(courseInfo[6]);
        course.setCredit(courseInfo[7]);
        return course;
    }

    public boolean checkDupleCourse(Course targetCourseNum){
        String targetCourseSubjectNum = targetCourseNum.getSubjectNum();
        String targetCourseClassNum = targetCourseNum.getClassNum();

        String currentCourseSubjectNum = "";
        String currentCourseClassNum = "";

        boolean isExist = false;

        if(!courseInfo.exists()) return isExist;
        try {
            FileReader fileReader = new FileReader(courseInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                currentCourseSubjectNum = line.split("/")[1];
                currentCourseClassNum = line.split("/")[3];
                if (currentCourseSubjectNum.equals(targetCourseSubjectNum)
                    &&currentCourseClassNum.equals(targetCourseClassNum)) {
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
    public boolean insertCourseInfo(Course newCourse){
        String newCourseInfo = newCourse.toString();
        try {
            FileWriter fileWriter = new FileWriter(COURSE_FILE_PATH,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newCourseInfo);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean updateCourseInfo(Course targetCourse) {
        boolean isUpdated = false;
        List<Course> courseList = getAllCoursesInfo();
        for(Course course : courseList){
            if(course.getCourseNum().equals(targetCourse.getCourseNum())){
                course.setSubjectNum(targetCourse.getSubjectNum());
                course.setCourseName(targetCourse.getCourseName());
                course.setClassNum(targetCourse.getClassNum());
                course.setProfessorId(targetCourse.getProfessorId());
                course.setSemester(targetCourse.getSemester());
                course.setTime(targetCourse.getTime());
                course.setCredit(targetCourse.getCredit());
                isUpdated = true;
                break;
            }
        }
        writeAllCourseInfo(courseList);
        return isUpdated;
    }

    public boolean deleteCourseInfo(String courseNum) {
        boolean isDeleted = false;
        List<Course> courseList = getAllCoursesInfo();
        for(Course course : courseList){
            if(course.getCourseNum().equals(courseNum)){
                courseList.remove(course);
                isDeleted = true;
                break;
            }
        }
        writeAllCourseInfo(courseList);
        return isDeleted;
    }

    public String getNextCourseNum() {
        int nextCourseNum = 77000001;
        try {
            if(courseNumberInfo.exists()){
                FileReader fileReader = new FileReader(courseNumberInfo);
                BufferedReader bufReader = new BufferedReader(fileReader);

                String line = "";
                if((line = bufReader.readLine()) != null){
                    nextCourseNum = Integer.parseInt(line);
                }
                bufReader.close();
                fileReader.close();
            }
            FileWriter fileWriter = new FileWriter(COURSE_NUM_FILE_PATH,false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(nextCourseNum + 1));
            bufferedWriter.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.toString(nextCourseNum);
    }

    public Course inquireCourseInfo(String courseNum) {
        Course selectedCourse = null;
        String currentCourseNum = "";
        try {
            if(!courseInfo.exists()) return selectedCourse;

            FileReader fileReader = new FileReader(courseInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                currentCourseNum = line.split("/")[0];
                if (currentCourseNum.equals(courseNum)) {
                    selectedCourse = convertStringToCourse(line);
                    break;
                }
            }
            bufReader.close();
            fileReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return selectedCourse;
    }

    public List<Course> inquireCoursesBySubjectNum(String targetSubjectNum) {
        String currentCourseNum="";
        Course foundCourse;

        List<Course> selectedCourseList = new ArrayList<>();

        try {
            if(!courseInfo.exists()) return selectedCourseList;
            FileReader fileReader = new FileReader(courseInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                currentCourseNum = line.split("/")[2];
                if (currentCourseNum.equals(targetSubjectNum)) {
                    foundCourse = convertStringToCourse(line);
                    selectedCourseList.add(foundCourse);
                }
            }
            bufReader.close();
            fileReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return selectedCourseList;

    }


    public List<Course> inquireCoursesByCourseName(String targetCourseName) {
        String currentCourseName="";
        Course foundCourse;

        List<Course> selectedCourseList = new ArrayList<>();

        try {
            if(!courseInfo.exists()) return selectedCourseList;
            FileReader fileReader = new FileReader(courseInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                currentCourseName = line.split("/")[2];
                if (currentCourseName.equals(targetCourseName)) {
                    foundCourse = convertStringToCourse(line);
                    selectedCourseList.add(foundCourse);
                }
            }
            bufReader.close();
            fileReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return selectedCourseList;

    }

    public List<Course> inquireCoursesByProfessorName(String targetProfessorName) {

        List<Course> selectedCourseList = new ArrayList<>();

        ProfessorManager professorManager  = new ProfessorManager();
        String targetProfessorId = professorManager.getProfessorId(targetProfessorName);
        if("".equals(targetProfessorId)) return selectedCourseList;

        String currentCourseProfessorId = "";
        Course selectedCourse;

        try {
            if(!courseInfo.exists()) return selectedCourseList;
            FileReader fileReader = new FileReader(courseInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                currentCourseProfessorId = line.split("/")[4];
                if (currentCourseProfessorId.equals(targetProfessorId)) {
                    selectedCourse = convertStringToCourse(line);
                    selectedCourseList.add(selectedCourse);
                }
            }
            bufReader.close();
            fileReader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return selectedCourseList;

    }
    private Comparator<Course> numComparator = new Comparator<Course>(){
        @Override
        public int compare(Course c1, Course c2) {
            return c1.getSubjectNum().compareTo(c2.getSubjectNum());
        }
    };
    private Comparator<Course> nameComparator = new Comparator<Course>(){
        @Override
        public int compare(Course c1, Course c2) {
            return c1.getCourseName().compareTo(c2.getCourseName());
        }
    };

    public List<Course> sortCoursesBySubjectNum() {
        List<Course> courseList = getAllCoursesInfo();
        Collections.sort(courseList,numComparator);
        return courseList;
    }

    public List<Course> sortCoursesByCourseName() {
        List<Course> courseList = getAllCoursesInfo();
        Collections.sort(courseList,nameComparator);
        return courseList;
    }

    public boolean findCourse(String courseNum) {
        String foundCourseNum = "";
        boolean isExist = false;

        if(!courseInfo.exists()) return isExist;
        try {
            FileReader fileReader = new FileReader(courseInfo);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufReader.readLine()) != null) {
                foundCourseNum = line.split("/")[0];
                if (foundCourseNum.equals(courseNum)) {
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
}



