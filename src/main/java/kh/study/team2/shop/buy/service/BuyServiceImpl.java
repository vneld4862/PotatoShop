package kh.study.team2.shop.buy.service;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.study.team2.shop.buy.vo.BuyVO;
import kh.study.team2.shop.item.vo.ItemVO;


@Service("buyService")
public class BuyServiceImpl implements BuyService{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	@Transactional(rollbackFor = Exception.class) //트랜잭션
	public void buyItem(BuyVO buyVO,ItemVO itemVO) {
		sqlSession.insert("buyMapper.buyItem", buyVO);
		sqlSession.update("buyMapper.memberInfoUpdate", itemVO);
		sqlSession.update("buyMapper.itemSalesStatus", buyVO); //트랜잭션 처리필요.
		sqlSession.update("cateMapper.updateMainChart",itemVO);
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
