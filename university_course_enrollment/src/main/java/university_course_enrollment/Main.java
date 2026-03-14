package university_course_enrollment;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CourseDAO dao = new CourseDAO();

        System.out.println("Enter courseName instructor studentName enrollmentDate");

        String courseName = sc.next();
        String instructor = sc.next();
        String studentName = sc.next();
        String date = sc.next();

        Course course = new Course();
        course.setCourseName(courseName);
        course.setInstructor(instructor);

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentName(studentName);
        enrollment.setEnrollmentDate(date);

        enrollment.setCourse(course);

        List<Enrollment> list = new ArrayList<>();
        list.add(enrollment);

        course.setEnrollments(list);

        dao.addCourse(course);

        dao.searchCourse(course.getId());
    }
}