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
	public List<ItemVO> selectItemList(ItemVO itemVO) {
		return sqlSession.selectList("itemMapper.selectItemList",itemVO);
	}

	@Override
	public ItemVO selectItemDetail(String itemCode) {
		return sqlSession.selectOne("itemMapper.selectItemDetail", itemCode);
	}

	@Override
	public List<ItemVO> memberItemList(String memberId) {
		return sqlSession.selectList("itemMapper.memberItemList", memberId);
	}

	@Override
	public void updateItem(ItemVO itemVO) {
	    sqlSession.update("itemMapper.updateItem", itemVO);
	}

	@Override
	public void deleteItem(String itemCode) {
		sqlSession.delete("itemMapper.deleteItem", itemCode);
	}

	@Override
	public int selectItemCnt(ItemVO itemVO) 
	{
		return sqlSession.selectOne("itemMapper.selectItemCnt",itemVO);
	}

}
