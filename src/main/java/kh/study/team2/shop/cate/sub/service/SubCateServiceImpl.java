package kh.study.team2.shop.cate.sub.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("subCateService")
public class SubCateServiceImpl implements SubCateService{
	@Autowired
	SqlSessionTemplate sqlSession;
}
