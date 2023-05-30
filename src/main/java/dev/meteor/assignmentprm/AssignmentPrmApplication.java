package dev.meteor.assignmentprm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AssignmentPrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignmentPrmApplication.class, args);
    }

}
