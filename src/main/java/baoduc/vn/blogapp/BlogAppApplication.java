package baoduc.vn.blogapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BlogAppApplication {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(BlogAppApplication.class, args);
//        String password = "1"; // Mật khẩu ban đầu
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        String encodedPassword = encoder.encode(password); // Mã hóa mật khẩu
//
//        System.out.println("Mật khẩu ban đầu: " + password);
//        System.out.println("Mật khẩu đã mã hóa: " + encodedPassword);
//        ;
    }

}
