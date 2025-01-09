package com.mysite.msa_exam.service;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysite.msa_exam.controller.ExamController;
import com.mysite.msa_exam.dao.ExamDao;
import com.mysite.msa_exam.dao.ExamMapper;
import com.mysite.msa_exam.dto.exam.ExamCateDTO;
import com.mysite.msa_exam.dto.exam.ExamCateFormDTO;
import com.mysite.msa_exam.dto.exam.ExamDTO;
import com.mysite.msa_exam.dto.exam.ExamFormDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamService {
	
	private final ExamMapper examMapper;

	public List<ExamDTO> getExamList(int pageNum) throws Exception {	
		return examMapper.getExamList(pageNum, 3);
	}

	public List<ExamDTO> getExamList2(int page, int pageSize) throws Exception {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("page", page);
		map.put("pageSize", pageSize);
		return examMapper.findAll(map);
	}

	public List<ExamCateDTO> getExamCateList(int em_cd, int page, int pageSize) throws Exception {
		HashMap<String, Object> map = new HashMap<>();
		map.put("em_cd", em_cd);
		map.put("page", page);
		map.put("pageSize", pageSize);
		//log.info("page = {}", examMapper.getExamCate(map));
		
		
		List<ExamCateDTO> examCateDTOs = examMapper.getExamCate(map);
		if(examCateDTOs.isEmpty()) {
			return null;
		} else {
			return examCateDTOs;
		}			
		
	}
	
	//안씀
	public Map<String, String> validHandling(BindingResult bindingResult) {
		Map<String, String> map = new HashMap<>();
		for(FieldError error : bindingResult.getFieldErrors()) {
			String validKeyName = String.format("valied_%s", error.getField());
			map.put(validKeyName, error.getDefaultMessage());
		}
		return map;
	}

	public void save(ExamFormDTO examFormDTO) {
		examMapper.examSave(examFormDTO);
	}

	public void update(ExamFormDTO examFormDTO) {
		examMapper.examUpdate(examFormDTO);
	}

	public void delete(int em_cd) {
		examMapper.examDelete(em_cd);		
	}

	public void cateSave(ExamCateFormDTO examCateFormDTO) {
		examMapper.examCateSave(examCateFormDTO);
	}

	public void cateUpdate(ExamCateFormDTO examCateFormDTO) {
		examMapper.examCateUpdate(examCateFormDTO);
	}

	public void cateDelete(int ec_cd) {
		examMapper.examCateDelete(ec_cd);		
	}
}
