package kh.study.team2.shop.sms.controller;

import java.util.Random;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.team2.shop.sms.service.SmsService;
import net.nurigo.java_sdk.exceptions.CoolsmsException;


@Controller
@RequestMapping("/sms")
public class SmsController {
	
	@ResponseBody
	@PostMapping("/sendSMS")
    public String sendSMS(String memberTell) throws CoolsmsException {

        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

		SmsService.certifiedPhoneNumber(memberTell, numStr);
        return numStr;
    }

}

