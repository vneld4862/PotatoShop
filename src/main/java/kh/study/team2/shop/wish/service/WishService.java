package kh.study.team2.shop.wish.service;

import java.util.List;

import kh.study.team2.shop.wish.vo.WishVO;

public interface WishService {
  
	void insertWish(WishVO wishVo);
	List<WishVO> selectWishList(String memberId);
	void deleteWish(WishVO wishVO);
	
}
