package kh.study.shop.cate.main.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainCateService")
public class MainCateServiceImpl implements MainCateService{
	@Autowired
	SqlSessionTemplate sqlSession;
}
