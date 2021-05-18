package com.example.springboot.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student sofia = new Student(
                "Sofia",
                "sofia@mail.com",
                LocalDate.of(1993, Month.MARCH, 24)
            );
            Student niki = new Student(
                "Jonathan",
                "jonathan@mail.com",
                LocalDate.of(1993, Month.JULY, 01)
            );
            Student ale = new Student(
                "Alejandra",
                "alejandra@mail.com",
                LocalDate.of(2014, Month.JUNE, 21)
            );

            repository.saveAll(
                List.of(sofia, niki, ale)
            );
        };
    }
}
