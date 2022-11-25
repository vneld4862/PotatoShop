package kh.study.team2.shop.qna.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.study.team2.shop.qna.service.QnaService;


@Controller
@RequestMapping("/qna")
public class QnaController {
	
	@Resource(name = "qnaService")
	QnaService qnaService;
	

	
	
}
