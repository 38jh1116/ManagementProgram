package main.view;

import main.Message;
import main.MyPrinter;
import main.MyScanner;
import main.model.Course;

import java.util.List;

public class CourseViewer extends MyPrinter {

    public String showMenu() {
        print("\n########### [4] 개설 강의 관리 ############\n");
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
        print("\n 새로 추가할 개설 강의 정보를 입력하세요.\n\n");
        print(" 과목 번호 : ");
        course.setSubjectNum(MyScanner.stringScanner());
        print(" 분반 : ");
        course.setClassNum(MyScanner.stringScanner());
        print(" 담당교수직번 : ");
        course.setProfessorNum(MyScanner.stringScanner());
        print(" 강의 학기 : ");
        course.setSemester(MyScanner.stringScanner());
        print(" 강의 시간 : ");
        course.setTime(MyScanner.stringScanner());

        return course;
    }

    public Course showModifyInfoCommand(Course course)
    {
        print(" 변경할 정보를 입력하세요.(변경사항이 없을 경우 : Enter)\n\n");

        print(" 과목 번호 : ");
        String subjectNum = MyScanner.stringScanner();
        subjectNum = "".equals(subjectNum) ? course.getSubject().getSubjectNum(): subjectNum;
        course.setSubjectNum(subjectNum);

        print(" 분반 : ");
        String classNum = MyScanner.stringScanner();
        classNum = "".equals(classNum) ? course.getClassNum(): classNum;
        course.setClassNum(classNum);

        print(" 담당 교수 직번 : ");
        String professorId = MyScanner.stringScanner();
        professorId = "".equals(professorId) ? course.getProfessor().getProfessorNum(): professorId;
        course.setProfessorNum(professorId);

        print(" 강의 학기 : ");
        String semester = MyScanner.stringScanner();
        semester = "".equals(semester) ? course.getSemester() : semester;
        course.setSemester(semester);

        print(" 강의 시간 : ");
        String time = MyScanner.stringScanner();
        time = "".equals(time) ? course.getTime(): time;
        course.setTime(time);

        return course;
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
        print("\n 1. 과목 번호 검색 \n");
        print(" 2. 과목명 검색\n");
        print(" 3. 교수명 검색\n");
        print(" 4. 과목 번호순 정렬\n");
        print(" 5. 과목 이름순 정렬\n");
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
        print("\n 과목 번호 >> ");
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

    public void showCourseInfo(Course targetCourse) {
        print("\n < 조회 정보 > ");
        print("\n강의 번호 : "+targetCourse.getCourseNum()+"\n");
        print(" 과목 번호 : " + targetCourse.getSubject().getSubjectNum()+"\n");
        print(" 과목 이름 : " + targetCourse.getSubject().getName()+"\n");
        print(" 분반 : " + targetCourse.getClassNum()+"\n");
        print(" 담당 교수 : " + targetCourse.getProfessor().getName()+"\n");
        print(" 담당 교수 직번 : " + targetCourse.getProfessor().getProfessorNum()+"\n");
        print(" 강의 학기 : " + targetCourse.getSemester()+"\n");
        print(" 강의 시간: " + targetCourse.getTime()+"\n");
        print(" 학점 : " + targetCourse.getSubject().getCredit());
    }

    public void showCoursesInfo(List<Course> targetCourses) {
        print("\n########################### 조회된 강의 정보 ##########################\n\n");
        print("[강의번호]\t\t[학수번호]\t  [강의명]\t\t\t [분반] [담당교수]\t\t[담당교수직번]\t[강의학기]\t  [강의시간]\t\t[학점]\n");
        for(Course course : targetCourses){
            printf("%-12s",course.getCourseNum());
            printf("%-10s",course.getSubject().getSubjectNum());
            printf("%-20s",course.getSubject().getName());
            printf("%-5s",course.getClassNum());
            printf("%-15s",course.getProfessor().getName());
            printf("%-10s",course.getProfessor().getProfessorNum());
            printf("%-10s",course.getSemester());
            printf("%-17s",course.getTime());
            printf("%-5s",course.getSubject().getCredit());
            print("\n");
        }
    }
}
