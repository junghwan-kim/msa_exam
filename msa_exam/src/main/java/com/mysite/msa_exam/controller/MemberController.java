package com.mysite.msa_exam.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.msa_exam.Pagination;
import com.mysite.msa_exam.dao.ExamMapper;
import com.mysite.msa_exam.dto.member.DailyCheckDTO;
import com.mysite.msa_exam.dto.member.MemberDTO;
import com.mysite.msa_exam.dto.member.MemberSearchDTO;
import com.mysite.msa_exam.service.ExamService;
import com.mysite.msa_exam.service.MemberService;

import ch.qos.logback.core.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/list")
	public String member_list(@ModelAttribute MemberSearchDTO searchDTO, 
			@RequestParam(value="page",defaultValue = "1") int page ,Model model) throws Exception {
				
			
		if(StringUtil.isNullOrEmpty(searchDTO.getStartDt()) || StringUtil.isNullOrEmpty(searchDTO.getEndDt())) {
			searchDTO.constructDt();
		}
		
		//System.out.println(searchDTO);
		
		int totalCnt =0;
		Pagination pagination = new Pagination();
		//페이지 사이즈, 블록 사이즈 설정 (기본은 10)
		pagination.setPageSize(15);
		pagination.setBlockSize(5);
		
		List<MemberDTO> memberList = memberService.getMemberList(page, pagination.getPageSize(), searchDTO);
		if(memberList != null) {
			totalCnt = memberList.getFirst().getTotalcnt();
		}

		pagination.pageInfo(totalCnt, page);
		//log.info("page = {}", memberList);

		/*
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
		model.addAttribute("g", "member");
		model.addAttribute("search", searchDTO);
		model.addAttribute("memberList", memberList);
		model.addAttribute("pagination",pagination);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pagination.getPageSize());
		model.addAttribute("totalCnt", totalCnt);
		return "/member/member_list";
	}
	
	@GetMapping("/daily_check_pop")
	public String daily_check(@RequestParam("mem_id") String mem_id
			,@RequestParam(value="page",defaultValue = "1") int page
			, Model model) throws Exception {
		
		int totalCnt =0;
		Pagination pagination = new Pagination();
		//페이지 사이즈, 블록 사이즈 설정 (기본은 10)
		pagination.setBlockSize(5);
		
		List<DailyCheckDTO> dailyCheckList = memberService.getDailyCheck(page, pagination.getPageSize(), mem_id);
		if(dailyCheckList != null) {
			totalCnt = dailyCheckList.getFirst().getTotalcnt();
		}
		pagination.pageInfo(totalCnt, page);
		
		model.addAttribute("dailyCheckList", dailyCheckList);
		model.addAttribute("pagination",pagination);
		model.addAttribute("mem_id", mem_id);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pagination.getPageSize());
		model.addAttribute("totalCnt", totalCnt);
		
		return "/member/pop/daily_check_pop";
	}
	
	
}
