package kh.study.team2.shop.sell.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.sell.vo.SellVO;

@Service("sellService")
public class SellServiceImpl implements SellService{

	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<SellVO> bestSaleItem() {
		return sqlSession.selectList("sellMapper.bestSaleItem");
	}
	
}
