package kh.study.team2.shop.item.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.study.team2.shop.item.vo.ImgVO;
import kh.study.team2.shop.item.vo.ItemVO;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//상품등록
	@Override
	@Transactional(rollbackFor = Exception.class) //트랜잭션 처리
	public void insertItem(ItemVO itemVO) {
		sqlSession.insert("itemMapper.insertItem", itemVO);
		sqlSession.insert("itemMapper.insertImgs", itemVO);
	}

	//다음에 등록될 상품코드 조회
	@Override
	public String getNextItemCode() {
	 return	sqlSession.selectOne("itemMapper.getNextItemCode");
		
	}

	//상품목록 조회
	@Override
	public List<ItemVO> selectItemList(ItemVO itemVO) {
		return sqlSession.selectList("itemMapper.selectItemList",itemVO);
	}

	//상품 상세조회
	@Override
	public ItemVO selectItemDetail(String itemCode) {
		return sqlSession.selectOne("itemMapper.selectItemDetail", itemCode);
	}

	//해당 회원의 상품목록
	@Override
	public List<ItemVO> memberItemList(ItemVO itemVO) {
		return sqlSession.selectList("itemMapper.memberItemList", itemVO);
	}

	//상품수정
	@Override
	@Transactional(rollbackFor = Exception.class) //트랜잭션
	public void updateItem(ItemVO itemVO) {
		
		if(itemVO.getImgList().size() != 0) {
			sqlSession.insert("itemMapper.insertImgs", itemVO);//조건
		}
	    sqlSession.update("itemMapper.updateItem", itemVO);
	    sqlSession.update("itemMapper.updateRevisionDate", itemVO);
	}

	//상품삭제
	@Override
	public void deleteItem(ItemVO itemVO) {
		sqlSession.delete("itemMapper.deleteItem", itemVO);
	}

	//상품수량 조회
	@Override
	public int selectItemCnt(ItemVO itemVO) 
	{
		return sqlSession.selectOne("itemMapper.selectItemCnt",itemVO);
	}

	@Override
	public List<ItemVO> searchItemName(ItemVO itemVO) {
		return sqlSession.selectList("itemMapper.searchItemName",itemVO);
	}
	@Override
	public List<ItemVO> searchCateName(ItemVO itemVO) {
		return sqlSession.selectList("itemMapper.searchCateName",itemVO);
	}

	@Override
	public int searchNameCnt(ItemVO itemVO) {
		return sqlSession.selectOne("itemMapper.searchNameCnt",itemVO);
	}
	@Override
	public int searchCateCnt(ItemVO itemVO) {
		return sqlSession.selectOne("itemMapper.searchCateCnt",itemVO);
	}
	@Override
	public void updateViewCnt(String itemCode) {
		sqlSession.update("itemMapper.updateViewCnt",itemCode);
	}

	@Override
	public List<ItemVO> bestFourSalersItem(List<String> memberList) {
		return sqlSession.selectList("itemMapper.bestSalersItem",memberList);
	}

	//해당 상점의 상품목록
	@Override
	public List<ItemVO> marketItemList(String itemCode) {
		return sqlSession.selectList("itemMapper.marketItemList", itemCode);
	}

	@Override
	public List<ImgVO> cookieItemList(List<String> attachedNameList) {
		return sqlSession.selectList("itemMapper.cookieItemList",attachedNameList);
	}

}
