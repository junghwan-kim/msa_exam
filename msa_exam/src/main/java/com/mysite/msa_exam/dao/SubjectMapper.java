package com.mysite.msa_exam.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mysite.msa_exam.dto.subject.SubjectFormDTO;
import com.mysite.msa_exam.dto.subject.subjectDTO;

@Mapper
@Repository
public interface SubjectMapper {

	List<subjectDTO> subjectList(HashMap<String, Object> map);

	public void saveSubject(SubjectFormDTO subjectFormDTO);

	List<HashMap<String, Object>> getKbnData();

	public void updateSubject(SubjectFormDTO subjectFormDTO);

}
