package com.mysite.msa_exam.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mysite.msa_exam.dto.member.DailyCheckDTO;
import com.mysite.msa_exam.dto.member.MemberDTO;

@Mapper
@Repository
public interface MemberMapper {

	List<MemberDTO> findAll(HashMap<String, Object> map);

	List<DailyCheckDTO> getDailyCheck(HashMap<String, Object> map);

}
