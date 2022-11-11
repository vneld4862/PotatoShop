package kh.study.team2.shop.cate.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.cate.vo.detail.DetailCateVO;
import kh.study.team2.shop.cate.vo.main.MainCateVO;
import kh.study.team2.shop.cate.vo.sub.SubCateVO;

@Service("cateService")
public class CateServiceImpl implements CateService{
	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public List<MainCateVO> mainCateList() {
		return sqlSession.selectList("cateMapper.mainCateList");
	}

	@Override
	public List<SubCateVO> subCateList(SubCateVO subCateVO) {
		return sqlSession.selectList("cateMapper.subCateList",subCateVO);
	}

	@Override
	public List<DetailCateVO> detailCateList(DetailCateVO detailCateVO) {
		return sqlSession.selectList("cateMapper.detailCateList",detailCateVO);
	}

	@Override
	public void inputMainCate(MainCateVO mainCateVO) {
		sqlSession.insert("cateMapper.inputMainCate",mainCateVO);
	}

	@Override
	public void inputSubCate(SubCateVO subCateVO) {
		sqlSession.insert("cateMapper.inputSubCate",subCateVO);
		
	}

	@Override
	public void inputDetailCate(DetailCateVO detailCateVO) {
		sqlSession.insert("cateMapper.inputDetailCate",detailCateVO);
	}

	@Override
	public List<SubCateVO> subCateInMainCate(String mainCateCode){
		return sqlSession.selectList("cateMapper.subCateInMainCate",mainCateCode);
	}

	@Override
	public String selectNextMainCateCode() {
		return sqlSession.selectOne("cateMapper.selectNextMainCateCode");
	}
	
	
}
