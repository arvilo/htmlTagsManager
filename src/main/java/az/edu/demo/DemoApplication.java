package az.edu.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    public String root() {
        StringBuilder sb = new StringBuilder();
        ListOfTags content = null;
        File file = new File("src/main/resources/tags.ser");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            content = (ListOfTags) ois.readObject();
        } catch (EOFException e) {
            content = new ListOfTags();
        } catch (Exception e) {
            e.printStackTrace();
        }
        content.tags.entrySet().stream().forEach(entry ->
                sb.append(entry.getValue().stringfy())
        );
        return sb.toString();
    }


}
