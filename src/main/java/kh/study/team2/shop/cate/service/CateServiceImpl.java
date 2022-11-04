package kh.study.team2.shop.cate.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.cate.vo.main.MainCateVO;

@Service("cateService")
public class CateServiceImpl implements CateService{
	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public List<MainCateVO> mainCateList() {
		return sqlSession.selectList("cateMapper.mainCateList");
	}
	
}
