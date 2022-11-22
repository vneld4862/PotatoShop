package kh.study.team2.shop.wish.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.item.vo.ItemVO;
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


	@Override
	public void deleteWish(WishVO wishVO) {
		sqlSession.delete("wishMapper.deleteWish", wishVO);
	}


	@Override
	public String selectWishCode(ItemVO itemVO) {
		return sqlSession.selectOne("wishMapper.selectWishCode", itemVO);
	}


	@Override
	public int wishAmount(String memberId) {
		return sqlSession.selectOne("wishMapper.selectWishAmount",memberId);
	}
	


}
