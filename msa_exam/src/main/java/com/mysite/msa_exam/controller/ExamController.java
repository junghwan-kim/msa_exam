package com.mysite.msa_exam.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysite.msa_exam.Pagination;
import com.mysite.msa_exam.dao.ExamMapper;
import com.mysite.msa_exam.dto.exam.ExamCateDTO;
import com.mysite.msa_exam.dto.exam.ExamCateFormDTO;
import com.mysite.msa_exam.dto.exam.ExamDTO;
import com.mysite.msa_exam.dto.exam.ExamFormDTO;
import com.mysite.msa_exam.dto.member.MemberSearchDTO;
import com.mysite.msa_exam.service.ExamService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/exam")
public class ExamController {
	
	private final ExamService examService;
	private final ExamMapper examMapper;
	/*
	@GetMapping("/list")
	public String findAll(@ModelAttribute SearchDTO searchDTO, 
			@RequestParam(value="page",defaultValue = "1") int pageNum ,Model model) throws Exception {
		
		//Page<ExamDTO> examDTOList = examService.getExamList2(pageNum);
		List<ExamDTO> examDTOList = examService.getExamList(pageNum);
		log.info("examDTOList = {}", examDTOList);
		model.addAttribute("examList", examDTOList);
		return "list";
	}
	*/
	
	@GetMapping("/list")
	public String findAll(@ModelAttribute MemberSearchDTO searchDTO, 
			@RequestParam(value="page",defaultValue = "1") int page ,Model model) throws Exception {
		
		int totalCnt = 0;
		Pagination pagination = new Pagination();
		
		//페이지 사이즈, 블록 사이즈 설정 (기본은 10)
		//pagination.setPageSize(2);
		//pagination.setBlockSize(3);

		//리스트와 리스트의 총 게시물수 
		List<ExamDTO> list2 = examService.getExamList2(page, pagination.getPageSize());
		totalCnt = list2.getFirst().getTotalcnt();
		
		pagination.pageInfo(totalCnt, page);
		
		/*
		 * 확인용
		System.out.println("전체글 수: "+pagination.getTotalListCnt()+ 
				" | 총페이지 수: "+pagination.getTotalPageCnt()+
				" | 현재페이지: "+pagination.getPage()+
				" | 이전페이지: "+pagination.getPrev()+
				" | 다음페이지: "+pagination.getNext()+
				" | 시작페이지: "+pagination.getStartPage()+
				" | 끝페이지: "+pagination.getEndPage()+
				" | 블럭사이즈: "+pagination.getBlockSize() +
				" | 현재 블럭: "+pagination.getBlock()	);
		*/
		//log.info("page = {}", list2);
		model.addAttribute("param1", "exam/list");
		model.addAttribute("examList", list2);
		model.addAttribute("pagination",pagination);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pagination.getPageSize());
		model.addAttribute("totalCnt", totalCnt);
		return "/exam/exam_list";
	}
	
	@PostMapping("/delete")
	public String ExamDelete(@RequestParam("em_cd") int em_cd) {
		examService.delete(em_cd);
		return "redirect:/exam/list";
	}

	@GetMapping("/exam_cate/{id}")
	public String ExamDetail(@PathVariable("id") int ec_em_cd
			,@RequestParam(value="page",defaultValue = "1") int page
			, Model model) throws Exception  {
		
		int totalCnt = 0;
		Pagination pagination = new Pagination();
		pagination.setPageSize(2);
		pagination.setBlockSize(3);
		List<ExamCateDTO> examCateList = examService.getExamCateList(ec_em_cd, page, pagination.getPageSize());
		if(examCateList != null) {
			totalCnt = examCateList.getFirst().getTotalcnt();
		}

		pagination.pageInfo(totalCnt, page);
		//log.info("page = {}", examCateList);
		model.addAttribute("param1", "exam/exam_cate/"+ec_em_cd);
		model.addAttribute("ec_em_cd", ec_em_cd);
		model.addAttribute("examCateList", examCateList);
		model.addAttribute("pagination",pagination);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pagination.getPageSize());
		model.addAttribute("totalCnt", totalCnt);
		return "/exam/exam_cate";
	}
	
	
	@GetMapping("/pop/exam")
	public String ExamInsert(ExamFormDTO examFormDTO, @RequestParam(value="mode", defaultValue="INS") String mode, Model model) {
		return "/exam/pop/exam";
		//@RequestParam(value="mode", defaultValue="INS")@PathVariable("id") Integer id
	}
	
	
	@PostMapping("/pop/exam")
	public String ExamInsert(@Valid ExamFormDTO examFormDTO, BindingResult bindingResult, Model model) {
		String mode = examFormDTO.getMode();
		
		//model.addAttribute("mode", mode);
		if(bindingResult.hasErrors()) {					
			return "/exam/pop/exam";
		}
					
		if(mode.equals("INS")) {
			examService.save(examFormDTO);
		} else {
			examService.update(examFormDTO);
		}
		
		int em_cd = examFormDTO.getEm_cd();
		
		model.addAttribute("em_cd",em_cd);
		model.addAttribute("page", examFormDTO.getPage());
		return "/exam/pop/exam";
		//return String.format("redirect:/exam/pop/exam?mode=%s", examFormDTO.getMode());
	}
	
	
	@GetMapping("/pop/exam_cate")
	public String ExamCateInsert(ExamCateFormDTO examCateFormDTO, @RequestParam(value="mode", defaultValue="INS_CATE") String mode, Model model) {
		return "/exam/pop/exam_cate";
	}
	
	@PostMapping("/pop/exam_cate")
	public String ExamCateInsert(@Valid ExamCateFormDTO examCateFormDTO, BindingResult bindingResult, Model model) {
		String mode = examCateFormDTO.getMode();
		if(bindingResult.hasErrors()) {
			return "/exam/pop/exam_cate";
		}
		if(mode.equals("INS_CATE")) {
			examService.cateSave(examCateFormDTO);
		} else {
			examService.cateUpdate(examCateFormDTO);
		}
		int ec_em_cd = examCateFormDTO.getEc_em_cd();
		int ec_cd = examCateFormDTO.getEc_cd();
		model.addAttribute("ec_em_cd",ec_em_cd);
		model.addAttribute("ec_cd",ec_cd);
		model.addAttribute("page", examCateFormDTO.getPage());
		return "/exam/pop/exam_cate";
	}
	
	@PostMapping("/exam_cate/delete")
	public String ExamDelete(@RequestParam("ec_cd") int ec_cd, @RequestParam("em_cd") int em_cd) {
		examService.cateDelete(ec_cd);
		return "redirect:/exam/exam_cate/"+em_cd;
	}
	
}
