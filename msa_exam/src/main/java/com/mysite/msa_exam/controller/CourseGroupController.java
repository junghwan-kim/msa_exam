package com.mysite.msa_exam.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.msa_exam.Pagination;
import com.mysite.msa_exam.dto.course_group.CourseGroupDTO;
import com.mysite.msa_exam.dto.course_group.CourseGroupSearchDTO;
import com.mysite.msa_exam.service.CourseGroupService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/course_group")
public class CourseGroupController {
	
	private final CourseGroupService courseGroupService;
	
	@GetMapping("/list")
	public String groupList(
			@ModelAttribute CourseGroupSearchDTO searchDTO
			,@RequestParam(value="page",defaultValue = "1") int page ,Model model) throws Exception {
		
		int totalCnt =0;
		Pagination pagination = new Pagination();
		
		pagination.setBlockSize(5);
		
		List<CourseGroupDTO> courseGroupList = courseGroupService.getCourseGroupList(page, pagination.getPageSize(), searchDTO);
		if(courseGroupList != null) {
			totalCnt = courseGroupList.getFirst().getTotal_cnt();
		}
		pagination.pageInfo(totalCnt, page);
				
		String strParams=null;
		strParams = "schClassType="+paramStringNullCheck(searchDTO.getSchClassType())
				+ "&schStr="+paramStringNullCheck(searchDTO.getSchStr())
				+ "&schView="+paramStringNullCheck(searchDTO.getSchView())
				+ "&page="+page;
		
		//System.out.println(strParams);
		
		model.addAttribute("search", searchDTO);
		model.addAttribute("strParams", strParams);		
		model.addAttribute("courseGroupList", courseGroupList);
		model.addAttribute("pagination",pagination);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pagination.getPageSize());
		model.addAttribute("totalCnt", totalCnt);
		
		return "/course_group/list";
	}
	
	@GetMapping("/chrgrp_ins")
	public String chrgrp_ins() {
		
		return "/course_group/chrgrp_ins";
	}
	
	public String paramStringNullCheck(String arg) {
		if(arg == null) {
			return "";
		} else {
			return arg;
		}
		
	}
}
