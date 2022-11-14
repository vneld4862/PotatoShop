package kh.study.team2.shop.cate.service;

import java.util.List;

import kh.study.team2.shop.cate.vo.detail.DetailCateVO;
import kh.study.team2.shop.cate.vo.main.MainCateVO;
import kh.study.team2.shop.cate.vo.sub.SubCateVO;

public interface CateService {
	List<MainCateVO> mainCateList();
	List<SubCateVO> subCateList(SubCateVO subCateVO);
	List<DetailCateVO> detailCateList(DetailCateVO detailCateVO);
	//mainCate 등록
	void inputMainCate(MainCateVO mainCateVO);
	//subCate 등록
	void inputSubCate(SubCateVO subCateVO);
	//detailCate 등록
	void inputDetailCate(DetailCateVO detailCateVO);
	//ajax로 detailCate등록 화면의 subCateList 조회
	List<SubCateVO> subCateInMainCate(String mainCateCode);
	
	//ajax로 등록한 카테고리 조회
	String selectNextMainCateCode();
	String selectNextSubCateCode();
	String selectNextDetailCateCode();
}
