package kh.study.team2.shop.manage.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.buy.vo.BuyVO;

@Service("manageService")
public class ManageServiceImpl implements ManageService{
	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public List<BuyVO> selectBuyList(String memberId) {
		return sqlSession.selectList("buyMapper.selectBuyList", memberId);
	}



	
}
