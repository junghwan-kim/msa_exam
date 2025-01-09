package com.mysite.msa_exam.dto.course_group;

import com.mysite.msa_exam.dto.exam.ExamCateFormDTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class CourseGroupDTO {

	private int cgm_cd;
	private String cgm_grp_cd;
	private String cgm_nm;
	private String cgm_memo;
	private String cgm_cls;
	private String cgm_view_yn;
	private String cgm_st_type;
	
	private int chr_cnt;
	private int row_no;
	private int total_cnt;
}
