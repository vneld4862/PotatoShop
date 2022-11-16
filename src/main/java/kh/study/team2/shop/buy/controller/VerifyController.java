package kh.study.team2.shop.buy.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/verifyIamport")
public class VerifyController {

    /** Iamport 결제 검증 컨트롤러 **/
    private final IamportClient iamportClient;

    // 생성자를 통해 REST API 와 REST API secret 입력
    public VerifyController(){
    	System.out.println("생성자 실행!!!");
        this.iamportClient = new IamportClient("2484135284404267", "pXDCcIqZJa1hYgxVOpG2i4Hxu80THB9RB6nDA7spSlzoYOoG9AEVKrx7Jrktaz07mmP5hxh217aE3UfO");
    }

    /** 프론트에서 받은 PG사 결괏값을 통해 아임포트 토큰 발행 **/
    @PostMapping("/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable String imp_uid) throws IamportResponseException, IOException{
    	System.out.println("paymentByImpUid 진입");
        return iamportClient.paymentByImpUid(imp_uid);
    }

}