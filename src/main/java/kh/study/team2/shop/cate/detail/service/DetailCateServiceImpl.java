package kh.study.team2.shop.cate.detail.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DetailCateService")
public class DetailCateServiceImpl implements DetailCateService{
	@Autowired
	SqlSessionTemplate sqlSession;
}
