package main.view;

import main.Message;
import main.MyPrinter;
import main.MyScanner;
import main.model.Course;

import java.util.List;

public class CourseViewer extends MyPrinter {

    public String showMenu() {
        print("\n########### [1] 강의 관리 ############\n");
        print("\n 1. 강의 정보 입력 \n");
        print(" 2. 강의 정보 조회\n");
        print(" 3. 강의 정보 수정 \n");
        print(" 4. 강의 정보 삭제 \n");
        print("------------------\n");
        print(" 0. 뒤로가기 \n\n");
        print(" >> ");
        return MyScanner.stringScanner();
    }
    public Course showCourseInfoInputCommand(){
        Course course = new Course();
        print("\n 새로 추가할 강의 정보를 입력하세요.\n\n");
        print(" 학수 번호 : ");
        course.setSubjectNum(MyScanner.stringScanner());
        print(" 강의명 : ");
        course.setCourseName(MyScanner.stringScanner());
        print(" 분반 : ");
        course.setClassNum(MyScanner.stringScanner());
        print(" 담당교수 : ");
        course.setProfessorId(MyScanner.stringScanner());
        print(" 강의 학기 : ");
        course.setSemester(MyScanner.stringScanner());
        print(" 강의 시간 : ");
        course.setTime(MyScanner.stringScanner());
        print(" 학점 : ");
        course.setCredit(MyScanner.stringScanner());
        return course;
    }

    public Course showModifyInfoCommand(Course course)
    {
        print(" 변경할 정보를 입력하세요. \n\n");

        print(" 학수 번호 : ");
        String subjectNum = MyScanner.stringScanner();
        subjectNum = "".equals(subjectNum) ? course.getSubjectNum(): subjectNum;
        course.setSubjectNum(subjectNum);

        print(" 강의명 : ");
        String courseName = MyScanner.stringScanner();
        courseName = "".equals(courseName) ? course.getCourseName() : courseName;
        course.setCourseName(courseName);

        print(" 분반 : ");
        String classNum = MyScanner.stringScanner();
        classNum = "".equals(classNum) ? course.getClassNum(): classNum;
        course.setClassNum(classNum);

        print(" 담당 교수 직번 : ");
        String professorId = MyScanner.stringScanner();
        professorId = "".equals(professorId) ? course.getProfessorId(): professorId;
        course.setProfessorId(professorId);

        print(" 강의 학기 : ");
        String semester = MyScanner.stringScanner();
        semester = "".equals(semester) ? course.getSemester() : semester;
        course.setSemester(semester);

        print(" 강의 시간 : ");
        String time = MyScanner.stringScanner();
        time = "".equals(time) ? course.getTime(): time;
        course.setTime(time);

        print(" 학점 : ");
        String credit = MyScanner.stringScanner();
        credit = "".equals(credit) ? course.getCredit() : credit;
        course.setCredit(credit);

        return course;
    }

 
    public void showCourseInfo(Course targetCourse) {
        print("\n < 조회 정보 > ");
        print("\n강의 번호 : "+targetCourse.getCourseNum()+"\n");
        print(" 학수 번호 : " + targetCourse.getSubjectNum()+"\n");
        print(" 강의명 : " + targetCourse.getCourseName()+"\n");
        print(" 분반 : " + targetCourse.getClassNum()+"\n");
        print(" 담당 교수: " + targetCourse.getProfessorId()+"\n");
        print(" 강의 학기 : " + targetCourse.getSemester()+"\n");
        print(" 강의 시간: " + targetCourse.getTime()+"\n");
        print(" 학점 : " + targetCourse.getCredit()+"\n\n");
    }
    public void showNotExistError() {
        print("\n"+Message.NOT_EXIST_ERROR+"\n");
    }

    public void showSaveSuccessMessage() {
        print("\n"+Message.SAVE_SUCCESS + "a new course information\n");
    }

    public void showSaveFailMessage() {
        print("\n"+Message.SAVE_FAIL + "a new course information\n");
    }

    public void showModifySuccessMessage() {
        print("\n"+Message.MODIFY_SUCCESS + "the course information\n");
    }

    public void showModifyFailMessage() {
        print("\n"+Message.MODIFY_FAIL + "the course information\n");
    }

    public void showRemoveSuccessMessage() {
        print("\n"+Message.REMOVE_SUCCESS + "the course information\n");
    }

    public void showRemoveFailMessage() {
        print("\n"+Message.REMOVE_FAIL + "the course information\n");
    }

    public void showSuccessInquireMessage() {
        print(Message.INQUIRE_SUCCESS + "the course information\n");
    }
    public void showNoDataMessage(){
        print("\n"+Message.NO_DATA + "in course file\n");
    }
    public String showInquireMenu() {
        print("\n 1. 학수 번호 검색 \n");
        print(" 2. 강의명 검색\n");
        print(" 3. 교수명 검색\n");
        print(" 4. 학수 번호순 정렬\n");
        print(" 5. 강의 이름순 정렬\n");
        print("------------------\n");
        print(" 0. 뒤로가기 \n\n");
        print(" >> ");
        return MyScanner.stringScanner();
    }
    
    public String showCourseNameCommand() {
        print("\n 강의명 >> ");
        return MyScanner.stringScanner();
    }
    public String showSubjectNumCommand() {
        print("\n 학수 번호 >> ");
        return MyScanner.stringScanner();
    }
    public String showCourseNumCommand() {
        print("\n강의 번호 >> ");
        return MyScanner.stringScanner();
    }
    public String showProfessorNameCommand() {
        print("\n교수명 >> ");
        return MyScanner.stringScanner();
    }


    public void showCoursesInfo(List<Course> targetCourses) {
        print("\n########################### 조회된 강의 정보 ##########################\n\n");
        print("[강의번호]\t\t[학수번호]\t  [강의명]\t\t\t [분반] [담당교수] \t[강의학기]\t\t  [강의시간]\t[학점]\n");
        for(Course course : targetCourses){
            printf("%-12s",course.getCourseNum());
            printf("%-10s",course.getSubjectNum());
            printf("%-20s",course.getCourseName());
            printf("%-5s",course.getClassNum());
            printf("%-10s",course.getProfessorId());
            printf("%-10s",course.getSemester());
            printf("%-15s",course.getTime());
            printf("%-5s",course.getCredit());
            print("\n");
        }
    }
}
