package com.mysite.msa_exam.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.msa_exam.dao.MemberMapper;
import com.mysite.msa_exam.dto.member.DailyCheckDTO;
import com.mysite.msa_exam.dto.member.MemberDTO;
import com.mysite.msa_exam.dto.member.MemberSearchDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberMapper memberMapper;

	public List<MemberDTO> getMemberList(int page, int pageSize, MemberSearchDTO searchDTO) throws Exception {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("pageSize", pageSize);
		map.put("search", searchDTO);
		
		List<MemberDTO> memberDTOs = memberMapper.findAll(map);
		if(memberDTOs.isEmpty()) {
			return null;
		} else {
			return memberDTOs;
		}	
	}

	public List<DailyCheckDTO> getDailyCheck(int page, int pageSize, String mem_id) throws Exception {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("pageSize", pageSize);
		map.put("mem_id", mem_id);
		List<DailyCheckDTO> dailyCheckDTOs = memberMapper.getDailyCheck(map);
		if(dailyCheckDTOs.isEmpty()) {
			return null;
		} else {	
			return dailyCheckDTOs;
		}
	}
}
