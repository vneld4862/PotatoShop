package kh.study.team2.sms.controller;

import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.team2.sms.service.smsService;

//@Controller
//@RequestMapping("/sms")
//public class smsController {
//
//	@Resource(name="smsService")
//	private smsService
//	
//	@ResponseBody
//	@GetMapping("/sendSMS")
//    public String sendSMS(String phoneNumber) {
//
//        Random rand  = new Random();
//        String numStr = "";
//        for(int i=0; i<4; i++) {
//            String ran = Integer.toString(rand.nextInt(10));
//            numStr+=ran;
//        }
//
//        System.out.println("수신자 번호 : " + phoneNumber);
//        System.out.println("인증번호 : " + numStr);
//        smsService.certifiedPhoneNumber(phoneNumber,numStr);
//        return numStr;
//    }

	    
//}
