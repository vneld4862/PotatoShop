package kh.study.shop.cate.sub.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainCateService")
public class SubCateServiceImpl implements SubCateService{
	@Autowired
	SqlSessionTemplate sqlSession;
}
