package kh.study.team2.shop.cate.service;

import java.util.List;

import kh.study.team2.shop.cate.vo.detail.DetailCateVO;
import kh.study.team2.shop.cate.vo.main.MainCateVO;
import kh.study.team2.shop.cate.vo.sub.SubCateVO;

public interface CateService {
	List<MainCateVO> mainCateList();
	List<SubCateVO> subCateList();
	List<DetailCateVO> detailCateList();
	//mainCate 등록
	void inputMainCate(MainCateVO mainCateVO);
	//subCate 등록
	void inputSubCate(SubCateVO subCateVO);
	//detailCate 등록
	void inputDetailCate(DetailCateVO detailCateVO);
}
