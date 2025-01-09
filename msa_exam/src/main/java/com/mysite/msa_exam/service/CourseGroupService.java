package com.mysite.msa_exam.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.msa_exam.dao.CourseGroupMapper;
import com.mysite.msa_exam.dto.course_group.CourseGroupDTO;
import com.mysite.msa_exam.dto.course_group.CourseGroupSearchDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseGroupService {
	private final CourseGroupMapper courseGroupMapper;

	public List<CourseGroupDTO> getCourseGroupList(int page, int pageSize, CourseGroupSearchDTO searchDTO) throws Exception {
		HashMap<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("pageSize", pageSize);
		map.put("search", searchDTO);
		
		List<CourseGroupDTO> groupDTOs = courseGroupMapper.getCourseGroupList(map);
		if(groupDTOs.isEmpty()) {
			return null;
		} else {
			return groupDTOs;
		}
	}
	
}
