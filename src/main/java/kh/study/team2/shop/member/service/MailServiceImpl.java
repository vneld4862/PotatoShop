package kh.study.team2.shop.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendPw(String memberEmail, String imsiPw) {
		SimpleMailMessage simpleMailMessage = new  SimpleMailMessage();
		simpleMailMessage.setTo(memberEmail);
		simpleMailMessage.setSubject("비밀번호 찾기");
		
		StringBuffer sb = new StringBuffer();
		sb.append("임시 비밀번호는");
		sb.append(System.lineSeparator());
		
		sb.append(imsiPw).append("입니다");
		
		simpleMailMessage.setText(sb.toString());
		
		mailSender.send(simpleMailMessage);
		
	}
}
