package com.example.hibernate.demo;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class CreateCourses {
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

            // get the instructor from db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            // create some courses
            Course c1 = new Course("Air-Guitar - The Ultimate Guide");
            Course c2 = new Course("The Pinball master Class");

            // add course to the instructor
            tempInstructor.add(c1);
            tempInstructor.add(c2);

            // save the courses
            //
            // Note: this will ALSO save the details object
            // because of CascadeType.ALL
            session.save(c1);
            session.save(c2);

            // commit transaction
            session.getTransaction().commit();
            log.info("Done!");
        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
    }
}
