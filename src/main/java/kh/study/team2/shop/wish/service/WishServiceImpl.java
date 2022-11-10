package kh.study.team2.shop.wish.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.wish.vo.WishVO;

@Service("wishService")
public class WishServiceImpl implements WishService{
 
	@Autowired
	SqlSessionTemplate sqlSession;
	
	
	@Override
	public void insertWish(WishVO wishVo) {
		sqlSession.insert("wishMapper.insertWish", wishVo);
	}

}
