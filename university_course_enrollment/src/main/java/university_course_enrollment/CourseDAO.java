package university_course_enrollment;

import jakarta.persistence.*;
import java.util.*;

public class CourseDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("university");
    EntityManager em = emf.createEntityManager();

    public void addCourse(Course course) {

        EntityTransaction et = em.getTransaction();
        et.begin();

        em.persist(course);

        et.commit();

        System.out.println("Course added successfully");
    }

    public void searchCourse(int id) {

        Course course = em.find(Course.class, id);

        if(course == null) {
            System.out.println("Course not found");
            return;
        }

        System.out.println("ID: " + course.getId());
        System.out.println("Course: " + course.getCourseName());
        System.out.println("Instructor: " + course.getInstructor());
        System.out.println("Enrollments:");

        for(Enrollment e : course.getEnrollments()) {
            System.out.println(" Student Name: " + e.getStudentName());
            System.out.println(" Enrollment Date: " + e.getEnrollmentDate());
        }
    }

    public void updateEnrollmentDate(int courseId, int enrollmentId, String newDate) {

        EntityTransaction et = em.getTransaction();
        et.begin();

        Course course = em.find(Course.class, courseId);

        if(course != null) {

            for(Enrollment e : course.getEnrollments()) {

                if(e.getId() == enrollmentId) {
                    e.setEnrollmentDate(newDate);
                }
            }

            em.merge(course);

            System.out.println("Enrollment updated successfully");
        }

        et.commit();
    }

    public void deleteCourse(int id) {

        EntityTransaction et = em.getTransaction();
        et.begin();

        Course course = em.find(Course.class, id);

        if(course != null) {

            em.remove(course);
            System.out.println("Course deleted successfully");
        }

        et.commit();
    }
}