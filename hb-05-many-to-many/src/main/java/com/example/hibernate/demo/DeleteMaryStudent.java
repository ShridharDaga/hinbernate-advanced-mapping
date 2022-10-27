package com.example.hibernate.demo;

import com.example.hibernate.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class DeleteMaryStudent {
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

            // delete student
            log.info("\nDeleting student: " + tempStudent + "\n");
            session.delete(tempStudent);

            // commit transaction
            session.getTransaction().commit();
            
            log.info("Done!\n");
        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
    }
}
