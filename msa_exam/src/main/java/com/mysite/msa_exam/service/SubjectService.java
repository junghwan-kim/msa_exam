package com.mysite.msa_exam.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.msa_exam.dao.SubjectMapper;
import com.mysite.msa_exam.dto.subject.SubjectFormDTO;
import com.mysite.msa_exam.dto.subject.subjectDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectService {

	private final SubjectMapper subjectMapper;
	
	public List<subjectDTO> getSubjectList(int page, String orderInfo, int pageSize) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("orderInfo", orderInfo);
		map.put("page", page);
		map.put("pageSize", pageSize);
		//System.out.println(map);
		return subjectMapper.subjectList(map);
		//return null;
	}

	public void saveSubject(SubjectFormDTO subjectFormDTO) {
		subjectMapper.saveSubject(subjectFormDTO);		
	}

	public List<HashMap<String, Object>> getKbnData() {
		return subjectMapper.getKbnData();
	}

	public void updateSubject(SubjectFormDTO subjectFormDTO) {
		subjectMapper.updateSubject(subjectFormDTO);
	}

}
