package com.example.hibernate.demo;

import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class Delete {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try(factory){

            //start a transaction
            session.beginTransaction();

            // get instructor by primary key / id
            int theId = 1;
            Instructor tempInstructor =
                    session.get(Instructor.class, theId);

            log.info("Found instructor: " + tempInstructor + "\n");

            // delete the instructors
            if (tempInstructor != null) {

                log.info("Deleting: " + tempInstructor + "\n");

                // Note: will ALSO delete associated "details" object
                // because of CascadeType.ALL
                session.delete(tempInstructor);
            }

            // commit transaction
            session.getTransaction().commit();
            log.info("Done!");
        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
    }
}
