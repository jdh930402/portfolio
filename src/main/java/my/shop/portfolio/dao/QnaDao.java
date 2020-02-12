package my.shop.portfolio.dao;

import java.util.ArrayList;

import my.shop.portfolio.command.QnaBean;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dto.QnaDto;

public interface QnaDao {
	
	// 해당 상품의 Q&A를 모두 출력
	public ArrayList<QnaDto> selectPrdQnaAll(Pagination pagination, QnaBean qnaBean);
	
	// 모든 상품의 Q&A를 모두 출력
	public ArrayList<QnaDto> selectQnaAll(Pagination pagination);
	
	// 특정 Q&A를 읽는다.
	public QnaDto selectQnaOne(QnaBean qnaBean);
	
	// 특정 Q&A의 답변을 읽는다.
	public QnaDto selectAnswerOne(QnaBean qnaBean);
	
	// 해당 상품의 	Q&A를 전체 개수를 출력
	public int countPrdQnaAll(QnaBean qnaBean);
	
	// 모든 상품의 Q&A를 전체 개수를 출력
	public int countQnaAll();
	
	// 마지막 gnum의 번호를 알기 위한 sql문
	public String selectLastGnum();
	
	// qna 등록
	public boolean insertQna(QnaBean qnaBean);
	
	// 답변 등록
	public boolean insertAnswer(QnaBean qnaBean);
	
	// qna에 답변 달렸을 경우 수정
	public boolean updateAnswerOk(QnaBean qnaBean);
	
	// qna에 답변 달려있는 경우 수정
	public boolean updateAnswer(QnaBean qnaBean);
	
	// qna 답변을 삭제
	public boolean deleteAnswer(QnaBean qnaBean);

}
