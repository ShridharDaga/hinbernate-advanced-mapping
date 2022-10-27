package com.example.hibernate.demo;

import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class DeleteInstructorDetail {
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
            int theId = 2;
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

                // remove the associated obj ref
                // break bidirectional link (if we want to delete instruction details but not instruction)
                tempInstructorDetail.getInstructor().setInstructorDetail(null);

                log.info("Deleting: " + tempInstructorDetail + "\n");
                session.delete(tempInstructorDetail);

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
