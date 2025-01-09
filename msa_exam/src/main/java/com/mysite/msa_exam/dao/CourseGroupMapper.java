package com.mysite.msa_exam.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mysite.msa_exam.dto.course_group.CourseGroupDTO;

@Mapper
@Repository
public interface CourseGroupMapper {

	List<CourseGroupDTO> getCourseGroupList(HashMap<String, Object> map);

}
