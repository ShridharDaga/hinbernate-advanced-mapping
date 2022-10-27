package com.example.hibernate.demo;

import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class GetInstructorDetail {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try(factory; session){

            //start a transaction
            session.beginTransaction();

            // get the instructor detail object
            int theId = 3;
            InstructorDetail tempInstructorDetail =
                    session.get(InstructorDetail.class, theId);

            if(tempInstructorDetail == null)
                log.warn("Record not found!");

            else {
                // print the instructor detail
                log.info("tempInstructorDetail: " + tempInstructorDetail + "\n");

                // print  the associated instructor
                log.info("the associated instructor: " +
                        tempInstructorDetail.getInstructor() + "\n");

                // commit transaction
                session.getTransaction().commit();
                log.info("Done!\n");
            }
        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
    }
}
