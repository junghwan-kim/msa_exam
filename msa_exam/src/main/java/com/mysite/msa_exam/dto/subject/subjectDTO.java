package com.mysite.msa_exam.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
public class subjectDTO {
	private String sm_dom_cd;
	private String dm_dom_nm;
	private String sm_sub_cd;
	private String sm_sub_nm;
	private String cc_kor_nm;
	private String sm_reg_dt;
	private String sm_upd_dt;
	private String sm_sel_type;
	private String sm_view_nm;
	private String sm_view_yn;
	private String sm_sub_type;
	private String sm_tg_tp;
	private int rownum;
	private int totalcnt;
}
