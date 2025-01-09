package com.mysite.msa_exam.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mysite.msa_exam.dto.exam.ExamDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ExamDao {
	
	private final SqlSessionTemplate sql;
	
	public List<ExamDTO> getExamList() {		
		return sql.selectList("Exam.getExamList");
	}
}
