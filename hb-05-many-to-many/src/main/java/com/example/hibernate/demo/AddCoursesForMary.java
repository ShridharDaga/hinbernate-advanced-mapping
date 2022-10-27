package com.example.hibernate.demo;

import com.example.hibernate.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class AddCoursesForMary {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try(factory; session){

            //start a transaction
            session.beginTransaction();

            // get the student mary from database
            int studentId = 2;
            Student tempStudent = session.get(Student.class, studentId);

            log.info("\nLoaded student: " + tempStudent + "\n");
            log.info("Courses: " + tempStudent.getCourses() + "\n");

            // create more courses
            Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
            Course tempCourse2 = new Course("Atari 2600 - Game Development");

            // add student to courses
            tempCourse1.addStudent(tempStudent);
            tempCourse2.addStudent(tempStudent);

            // save the courses
            log.info("Saving the courses ...\n");

            session.save(tempCourse1);
            session.save(tempCourse2);

            // commit transaction
            session.getTransaction().commit();
            
            log.info("Done!\n");
        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
    }
}
