package kh.study.team2.shop.item.service;

import java.util.List;

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

	@Override
	public List<ItemVO> selectItemList() {
		return sqlSession.selectList("itemMapper.selectItemList");
	}

	@Override
	public ItemVO selectItemDetail(String itemCode) {
		return sqlSession.selectOne("itemMapper.selectItemDetail", itemCode);
	}

	@Override
	public List<ItemVO> memberItemList(String memberId) {
		return sqlSession.selectList("itemMapper.memberItemList", memberId);
	}

//	@Override
//	public ItemVO updateItem(ItemVO itemVO) {
//		return sqlSession.update("itemMapper.updateItem", itemVO);
//	}

}
