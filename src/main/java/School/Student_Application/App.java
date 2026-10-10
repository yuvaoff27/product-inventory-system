package School.Student_Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Make sure it looks clean like this without the (exclude = ...) block
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
