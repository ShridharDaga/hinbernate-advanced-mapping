package com.example.hibernate.demo;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class CreateInstructor {
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

            // create the objects
            Instructor tempInstructor =
                    new Instructor("Gaurav", "Taneja", "flyingbeast@gmail.com");
            InstructorDetail tempInstructorDetail =
                    new InstructorDetail("http://www.youtube.com/flyingbeast320", "Vlogging");

            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            //start a transaction
            session.beginTransaction();

            // save the instructor
            //
            // Note: this will ALSO save the details object
            // because of CascadeType.ALL
            //
            log.info("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            // commit transaction
            session.getTransaction().commit();
            log.info("Done!");
        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
    }
}
