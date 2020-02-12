package my.shop.portfolio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.shop.portfolio.command.QnaBean;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dao.QnaDao;
import my.shop.portfolio.dto.QnaDto;
import my.shop.portfolio.mapper.QnaMapper;

@Repository("qnaDao")
public class QnaDaoImpl implements QnaDao{
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Override
	// 해당 상품의 Q&A를 모두 출력
	public ArrayList<QnaDto> selectPrdQnaAll(Pagination pagination, QnaBean qnaBean) {
		return qnaMapper.selectPrdQnaAll(pagination, qnaBean);
	}
	
	@Override
	// 모든 상품의 Q&A를 모두 출력
	public ArrayList<QnaDto> selectQnaAll(Pagination pagination) {
		return qnaMapper.selectQnaAll(pagination);
	}
	
	@Override
	// 특정 Q&A를 읽는다.
	public QnaDto selectQnaOne(QnaBean qnaBean) {
		return qnaMapper.selectQnaOne(qnaBean);
	}
	
	@Override
	// 특정 Q&A의 답변을 읽는다.
	public QnaDto selectAnswerOne(QnaBean qnaBean) {
		return qnaMapper.selectAnswerOne(qnaBean);
	}
	
	@Override
	// 해당 상품의 	Q&A를 전체 개수를 출력
	public int countPrdQnaAll(QnaBean qnaBean) {
		return qnaMapper.countPrdQnaAll(qnaBean);
	}
	
	@Override
	// Q&A를 전체 개수를 출력
	public int countQnaAll() {
		return qnaMapper.countQnaAll();
	}
	
	@Override
	// 마지막 gnum의 번호를 알기 위한 sql문
	public String selectLastGnum() {
		return qnaMapper.selectLastGnum();
	}
	
	@Override
	// qna 등록
	public boolean insertQna(QnaBean qnaBean) {
		return qnaMapper.insertQna(qnaBean);
	}
	
	@Override
	// 답변 등록
	public boolean insertAnswer(QnaBean qnaBean) {
		return qnaMapper.insertAnswer(qnaBean);
	}
	
	@Override
	// qna에 답변 달렸을 경우 수정
	public boolean updateAnswerOk(QnaBean qnaBean) {
		return qnaMapper.updateAnswerOk(qnaBean);
	}
	
	@Override
	// qna에 답변 달려있는 경우 수정
	public boolean updateAnswer(QnaBean qnaBean) {
		return qnaMapper.updateAnswer(qnaBean);
	}
	
	@Override
	// qna 답변을 삭제
	public boolean deleteAnswer(QnaBean qnaBean) {
		return qnaMapper.deleteAnswer(qnaBean);
	}
}
