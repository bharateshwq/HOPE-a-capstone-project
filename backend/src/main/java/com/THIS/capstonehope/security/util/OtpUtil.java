package com.THIS.capstonehope.security.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class OtpUtil {
    public String generateOtp() {
        Random random = new Random();
        int randomNumber = random.nextInt(9999);
        String output = Integer.toString(randomNumber);
        //if otp happens to be 0012
        while (output.length()<4){
            output = "0" + output;
        }
        return output;
    }




}
