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
	public List<SubCateVO> subCateList() {
		return sqlSession.selectList("cateMapper.subCateList");
	}

	@Override
	public List<DetailCateVO> detailCateList() {
		return sqlSession.selectList("cateMapper.detailCateList");
	}

	@Override
	public void inputMainCate(MainCateVO mainCateVO) {
		sqlSession.insert("cateMapper.inputMainCate",mainCateVO);
		
	}

	@Override
	public void inputSubCate(SubCateVO subCateVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputDetailCate(DetailCateVO detailCateVO) {
		// TODO Auto-generated method stub
		
	}
	
	
}
