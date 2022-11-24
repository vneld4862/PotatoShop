package kh.study.team2.shop.buy.service;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.buy.vo.BuyVO;


@Service("buyService")
public class BuyServiceImpl implements BuyService{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void buyItem(BuyVO buyVO) {
		sqlSession.insert("buyMapper.buyItem", buyVO);
	}

	@Override
	public String selectNextBuyCode() {
		return sqlSession.selectOne("buyMapper.selectNextBuyCode");
	}

	@Override
	public void updateBuyConfirm(String itemCode) {
		sqlSession.update("buyMapper.updateBuyConfirm", itemCode);
	}

	
	


	

	
	
	
}
