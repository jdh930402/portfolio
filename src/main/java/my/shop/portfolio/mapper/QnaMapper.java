package my.shop.portfolio.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import my.shop.portfolio.command.QnaBean;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dto.QnaDto;

public interface QnaMapper {
	
	// 해당 상품의 Q&A를 모두 출력
	@Select("SELECT id, product_id, title, content, user_email, gnum, is_answer, date_format(regdate, '%Y-%m-%d %H:%i:%s') as regdate FROM qna WHERE NOT is_answer IN(2) AND product_id = #{qna.product_id} ORDER BY regdate DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<QnaDto> selectPrdQnaAll(@Param("paging") Pagination pagination, @Param("qna") QnaBean qnaBean);
	
	// 모든 상품의 Q&A를 모두 출력
	@Select("SELECT id, product_id, title, content, user_email, gnum, is_answer, date_format(regdate, '%Y-%m-%d %H:%i:%s') as regdate FROM qna WHERE NOT is_answer IN(2) ORDER BY regdate DESC LIMIT #{start}, #{length}")
	public ArrayList<QnaDto> selectQnaAll(Pagination pagination);
	
	// 해당 상품의 	Q&A를 전체 개수를 출력
	@Select("SELECT count(id) from qna WHERE NOT is_answer IN(2) AND product_id=#{product_id}")
	public int countPrdQnaAll(QnaBean qnaBean);
	
	// 특정 Q&A를 읽는다.
	@Select("SELECT id, product_id, title, content, user_email, gnum, is_answer, date_format(regdate, '%Y-%m-%d %H:%i:%s') as regdate FROM qna WHERE id = #{id}")
	public QnaDto selectQnaOne(QnaBean qnaBean);

	// 특정 Q&A의 답변을 읽는다.
	@Select("SELECT id, product_id, title, content, user_email, gnum, is_answer, date_format(regdate, '%Y-%m-%d %H:%i:%s') as regdate FROM qna WHERE gnum = #{gnum} AND is_answer = 2")
	public QnaDto selectAnswerOne(QnaBean qnaBean);
	
	
	// 모든 상품의 Q&A를 전체 개수를 출력
	@Select("SELECT count(id) from qna WHERE NOT is_answer IN(2)")
	public int countQnaAll();
	
	// 마지막 gnum의 번호를 알기 위한 sql문
	@Select("SELECT gnum FROM qna WHERE NOT is_answer IN(2) ORDER BY gnum DESC limit 1")
	public String selectLastGnum();
	
	// qna 등록
	@Insert("INSERT INTO qna(product_id, title, content, user_email, gnum) VALUES(#{product_id}, #{title}, #{content}, #{user_email}, #{gnum} )")
	public boolean insertQna(QnaBean qnaBean);
	
	// 답변 등록
	@Insert("INSERT INTO qna(product_id, content, user_email, gnum, is_answer) VALUES(#{product_id}, #{content}, #{user_email}, #{gnum}, 2)")
	public boolean insertAnswer(QnaBean qnaBean);
	
	// qna에 답변 달렸을 경우 수정
	@Update("UPDATE qna SET is_answer = #{is_answer} WHERE id = #{id}")
	public boolean updateAnswerOk(QnaBean qnaBean);
	
	// qna에 답변 달려있는 경우 수정
	@Update("UPDATE qna SET content = #{content} WHERE gnum = #{gnum} AND is_answer = 2")
	public boolean updateAnswer(QnaBean qnaBean);
	
	// qna 답변을 삭제
	@Delete("DELETE FROM qna WHERE gnum = #{gnum} AND is_answer = 2")
	public boolean deleteAnswer(QnaBean qnaBean);
} 
