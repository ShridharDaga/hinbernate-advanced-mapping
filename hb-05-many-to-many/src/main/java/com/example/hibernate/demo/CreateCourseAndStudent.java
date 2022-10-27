package com.example.hibernate.demo;

import com.example.hibernate.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class CreateCourseAndStudent {
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

            // create a course
            Course tempCourse = new Course("Pacman - How To Score One Million Points");

            // save the course ... and leverage the cascade all :-)
            log.info("Saving the course...\n");
            session.save(tempCourse);
            log.info("Saved the Course : " + tempCourse + "\n");

            // create the students
            Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
            Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");

            // add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            // save the students
            log.info("\nSaving students ...\n");
            session.save(tempStudent1);
            session.save(tempStudent2);
            log.info("Saved students: " + tempCourse.getStudents() + "\n");

            // commit transaction
            session.getTransaction().commit();

            log.info("Done!\n");
        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
    }
}
