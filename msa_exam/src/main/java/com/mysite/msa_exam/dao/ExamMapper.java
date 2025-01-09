package com.mysite.msa_exam.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.mysite.msa_exam.dto.exam.ExamCateDTO;
import com.mysite.msa_exam.dto.exam.ExamCateFormDTO;
import com.mysite.msa_exam.dto.exam.ExamDTO;
import com.mysite.msa_exam.dto.exam.ExamFormDTO;

import jakarta.validation.Valid;

@Mapper
@Repository
public interface ExamMapper {
	
	//파라미터 그대로 넘김
	public List<ExamDTO> getExamList(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

	public List<ExamDTO> getExamList2();
	
	//map을 만들어서 넘김
	public List<ExamDTO> findAll(HashMap<String, Integer> map);

	public List<ExamCateDTO> getExamCate(HashMap<String, Object> map);

	public void examSave(ExamFormDTO examFormDTO);

	public void examUpdate(ExamFormDTO examFormDTO);

	public void examDelete(int em_cd);

	public void examCateSave(ExamCateFormDTO examCateFormDTO);

	public void examCateUpdate(ExamCateFormDTO examCateFormDTO);

	public void examCateDelete(int ec_cd);
	
}
