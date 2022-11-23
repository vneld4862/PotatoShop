package kh.study.team2.shop.cate.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.study.team2.shop.cate.service.CateService;
import kh.study.team2.shop.cate.vo.detail.DetailCateVO;
import kh.study.team2.shop.cate.vo.sub.SubCateVO;

@Controller
@RequestMapping("/cate")
public class CateController 
{
	@Resource(name = "cateService")
	private CateService cateService;
	
	@ResponseBody
	@PostMapping("/selectSubCateAjax")
	public List<SubCateVO> selectSubCateAjax(SubCateVO subCateVO)
	{
		return cateService.subCateList(subCateVO);
	}
	@ResponseBody
	@PostMapping("/selectDetailCateAjax")
	public List<DetailCateVO> selectDetailCateAjax(DetailCateVO detailCateVO)
	{
		return cateService.detailCateList(detailCateVO);
	}
}
