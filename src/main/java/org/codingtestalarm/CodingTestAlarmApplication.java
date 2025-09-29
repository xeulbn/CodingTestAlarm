package org.codingtestalarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CodingTestAlarmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodingTestAlarmApplication.class, args);
    }

}
