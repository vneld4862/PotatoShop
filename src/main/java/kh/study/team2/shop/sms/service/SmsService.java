package kh.study.team2.shop.sms.service;

import java.util.HashMap;


import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SmsService {

	
	//휴대폰번호 인증문자 보내기
	public static void certifiedPhoneNumber(String memberTell, String numStr) {
		String api_key = "NCSZS0G2GINDPAUV";
		String api_secret = "AYMYE6S0FADZF1SJURL8KKSSFM89H7H6";
		Message coolsms = new Message(api_key, api_secret);
			
		  
		HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", memberTell);    // 수신전화번호 (ajax로 view 화면에서 받아온 값으로 넘김)
	    params.put("from", "01040365367");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
	    params.put("type", "sms"); 
	    params.put("text", "감자마켓 휴대폰 인증번호는 [" + numStr + "] 입니다.");
	    params.put("app_version", "test app 1.2"); // application name and version
			  
	    try {
	    	coolsms.send(params); // 메세지 전송
	      } catch (CoolsmsException e) {
	    	  e.printStackTrace();
	      }	  
		 
	}
	
}
