package com.mysite.msa_exam.dto.exam;

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
public class ExamDTO {
	private int em_cd;
	private String em_dom_cd;
	private String em_nm;
	private String em_nm_kr;
	private String em_view_yn;
	private String em_reg_dt;
	private int rownum;
	private int totalcnt;
}
