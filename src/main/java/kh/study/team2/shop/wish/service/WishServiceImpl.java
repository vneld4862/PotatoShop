package kh.study.team2.shop.wish.service;

import java.util.List;

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


	@Override
	public List<WishVO> selectWishList(String memberId) {
		return sqlSession.selectList("wishMapper.selectWishList", memberId);
	}

}
