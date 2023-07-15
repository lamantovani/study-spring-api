package med.voll.api;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("encoder 123456:  " + encoder.encode("123456"));
        System.out.println("encoder:  " + encoder.encode("abel"));
        System.out.println("encoder:  " + encoder.encode("admin"));

        if (encoder.matches("abel", "$2a$10$IAz6WzJ314LH1NXq7Rf.dOYPP2uvzk08g.eAl9l4DRG4YsxavEV4W")) {
            System.out.println("encoder: true");
        }

    }
}

