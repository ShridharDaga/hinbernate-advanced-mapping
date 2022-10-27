package com.example.hibernate.demo;

import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Instructor;
import com.example.hibernate.entity.InstructorDetail;
import com.example.hibernate.entity.Review;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class CreateCourseAndReview {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try(factory; session){

            //start a transaction
            session.beginTransaction();

            // create a course
            Course tempCourse = new Course("Pacman - How To Score One Million Points");

            // add some reviews
            tempCourse.addReview(new Review("Great course ... loved it!"));
            tempCourse.addReview(new Review("Cool course, job well done"));
            tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

            // save the course ... and leverage the cascade all :-)
            log.info("Saving the course");
            log.info("Course : " + tempCourse + "\n");
            log.info("Review : " + tempCourse.getReviews() + "\n");

            session.save(tempCourse);

            // commit transaction
            session.getTransaction().commit();
            log.info("Done!\n");
        }
        catch (Exception ex){
            log.info("Exception occurred : " + ex);
        }
    }
}
