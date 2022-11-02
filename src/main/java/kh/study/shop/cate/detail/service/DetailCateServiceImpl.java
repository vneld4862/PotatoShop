package kh.study.shop.cate.detail.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainCateService")
public class DetailCateServiceImpl implements DetailCateService{
	@Autowired
	SqlSessionTemplate sqlSession;
}
