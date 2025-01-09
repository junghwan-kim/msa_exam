package com.mysite.msa_exam.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.msa_exam.Pagination;
import com.mysite.msa_exam.dto.subject.SubjectFormDTO;
import com.mysite.msa_exam.dto.subject.subjectDTO;
import com.mysite.msa_exam.service.SubjectService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectController {
	
	private final SubjectService subjectService;
	
	@GetMapping("/list")
	public String subjectList(@RequestParam(value = "page", defaultValue = "1") int page
			,@RequestParam(value="odr",defaultValue = "sm_sub_cd") String odr
			,@RequestParam(value="srt",defaultValue = "asc") String srt
			, Model model) throws Exception {
		
		int totalCnt =0;
		Pagination pagination = new Pagination();
		//페이지 사이즈, 블록 사이즈 설정 (기본은 10)
		pagination.setPageSize(20);
		pagination.setBlockSize(5);
		
		String orderInfo = odr+' '+srt;
		
		List<subjectDTO> list = subjectService.getSubjectList(page, orderInfo, pagination.getPageSize());
		if(list != null) {
			totalCnt = list.getFirst().getTotalcnt();
		}

		pagination.pageInfo(totalCnt, page);

		model.addAttribute("subjectList", list);
		model.addAttribute("pagination",pagination);
		model.addAttribute("currentPage", page);
		model.addAttribute("odr", odr);
		model.addAttribute("srt", srt);
		model.addAttribute("pageSize", pagination.getPageSize());
		model.addAttribute("totalCnt", totalCnt);
		return "/subject/list";
	}
	
	@GetMapping("/sub_act")
	public String sub_act(SubjectFormDTO subjectFormDTO
			,@RequestParam(value = "method", defaultValue = "new") String method
			,@RequestParam(value = "subtg", defaultValue = "4") int subtg
			,@RequestParam(value = "subkbn", defaultValue = "1") String subkbn
			, Model model) throws Exception {	
		
		
		if(method.equals("upd")) {
			subjectFormDTO.setOld_subcd(subjectFormDTO.getSubcd());
			subjectFormDTO.setSub_tg(subtg);
			if(subkbn.equals("common")) {
				subjectFormDTO.setSubkbn("1");
			} else {
				subjectFormDTO.setSubkbn("2");
			}
		}
		
		List<HashMap<String, Object>> kbn = subjectService.getKbnData();
		System.out.println(subjectFormDTO);
		//log.info("subjectForm = {}", subjectFormDTO);
		model.addAttribute("kbnList", kbn);
		return "/subject/pop/sub_act";
	}
	
	@PostMapping("/sub_act")
	public String sub_act(@Valid SubjectFormDTO subjectFormDTO, BindingResult bindingResult, Model model) throws Exception {
		
		String method = subjectFormDTO.getMethod();
		List<HashMap<String, Object>> kbn = subjectService.getKbnData();
		model.addAttribute("kbnList", kbn);		
		if(bindingResult.hasErrors()) {
			return "/subject/pop/sub_act";
		}
		
		if(method.equals("new")) {
			subjectService.saveSubject(subjectFormDTO);
		} else {
			System.out.println(subjectFormDTO);
			subjectService.updateSubject(subjectFormDTO);
		}
		model.addAttribute("success", "1");
		model.addAttribute("method",method);
		model.addAttribute("page", subjectFormDTO.getPage());
		return "/subject/pop/sub_act";
	}
	
}
