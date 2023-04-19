package ru.mpei.example;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.mpei.example.repositories.StudentRepository;
import ru.mpei.example.repositories.StudentRepositoryJpa;

import java.sql.SQLException;

@SpringBootApplication
public class MainOrm {


    public static void main(String[] args) throws SQLException {
       ConfigurableApplicationContext applicationContext = SpringApplication.run(MainOrm.class, args);
        EntityManagerFactory entityManagerFactory = applicationContext.getBean(EntityManagerFactory.class);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        StudentRepository studentRepository = new StudentRepositoryJpa(entityManager);
        System.out.println(studentRepository.shomMeGroupAndId());
        System.out.println(studentRepository.getAverageGrade(3));

        //Console.main(args);
    }

}
