package kh.study.team2.shop.cate.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.team2.shop.cate.service.CateService;
import kh.study.team2.shop.cate.vo.sub.SubCateVO;

@Controller
@RequestMapping("/cate")
public class CateController 
{
	@Resource(name = "cateService")
	private CateService cateService;
	
	@ResponseBody
	@PostMapping("/selectSubCateAjax")
	public List<SubCateVO> selectSubCateAjax(SubCateVO cateVO)
	{
		return cateService.subCateList(cateVO);
	}
}
