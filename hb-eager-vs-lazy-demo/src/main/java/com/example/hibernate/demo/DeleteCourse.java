package com.example.hibernate.demo;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class DeleteCourse {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try(factory; session){

            //start a transaction
            session.beginTransaction();

            // get a course
            int theId = 10;
            Course tempCourse =
                    session.get(Course.class, theId);

            if(tempCourse == null)
                log.warn("Record not found!");

            else {
                log.info("Deleting: " + tempCourse + "\n");
                session.delete(tempCourse);
            }

            // commit transaction
            session.getTransaction().commit();
            log.info("Done!\n");

        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
    }
}
