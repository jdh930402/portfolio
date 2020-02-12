package my.shop.portfolio.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.shop.portfolio.command.QnaBean;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dao.QnaDao;
import my.shop.portfolio.dto.QnaDto;
import my.shop.portfolio.service.QnaService;

@Service("qnaService")
public class QnaServiceImpl implements QnaService{
	
	@Autowired
	private QnaDao qnaDao;
	
	@Override
	// 해당 상품의 Q&A를 모두 출력
	public ArrayList<QnaDto> selectPrdQnaAll(Pagination pagination, QnaBean qnaBean) {
		return qnaDao.selectPrdQnaAll(pagination, qnaBean);
	}
	
	@Override
	// 모든 상품의 Q&A를 모두 출력
	public ArrayList<QnaDto> selectQnaAll(Pagination pagination) {
		return qnaDao.selectQnaAll(pagination);
	}
	
	@Override
	// 특정 Q&A를 읽는다.
	public QnaDto selectQnaOne(QnaBean qnaBean) {
		return qnaDao.selectQnaOne(qnaBean);
	}
	
	@Override
	// 특정 Q&A의 답변을 읽는다.
	public QnaDto selectAnswerOne(QnaBean qnaBean) {
		return qnaDao.selectAnswerOne(qnaBean);
	}
	
	@Override
	public int countPrdQnaAll(QnaBean qnaBean) {
		return qnaDao.countPrdQnaAll(qnaBean);
	}
	
	@Override
	// Q&A를 전체 개수를 출력
	public int countQnaAll() {
		return qnaDao.countQnaAll();
	}
	
	@Override
	// 마지막 gnum의 번호를 알기 위한 sql문
	public String selectLastGnum() {
		return qnaDao.selectLastGnum();
	}
	
	@Override
	// qna 등록
	public boolean insertQna(QnaBean qnaBean) {
		return qnaDao.insertQna(qnaBean);
	}
	
	@Override
	// 답변 등록
	public boolean insertAnswer(QnaBean qnaBean) {
		return qnaDao.insertAnswer(qnaBean);
	}
	
	@Override
	// qna에 답변 달렸을 경우 수정
	public boolean updateAnswerOk(QnaBean qnaBean) {
		return qnaDao.updateAnswerOk(qnaBean);
	}
	
	@Override
	// qna에 답변 달려있는 경우 수정
	public boolean updateAnswer(QnaBean qnaBean) {
		return qnaDao.updateAnswer(qnaBean);
	}
	
	@Override
	// qna 답변을 삭제
	public boolean deleteAnswer(QnaBean qnaBean) {
		return qnaDao.deleteAnswer(qnaBean);
	}
}
