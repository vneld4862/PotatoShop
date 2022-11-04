package kh.study.team2.shop.item.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.study.team2.shop.item.vo.ItemVO;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void insertItem(ItemVO itemVO) {
		sqlSession.insert("itemMapper.insertItem", itemVO);
		sqlSession.insert("itemMapper.insertImgs", itemVO);
	}

	@Override
	public String getNextItemCode() {
	 return	sqlSession.selectOne("itemMapper.getNextItemCode");
		
	}

}
