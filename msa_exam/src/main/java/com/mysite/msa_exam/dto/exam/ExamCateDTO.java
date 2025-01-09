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
public class ExamCateDTO {
	private int ec_cd;
	private int ec_em_cd;
	private String em_nm;
	private String ec_nm;
	private String ec_part_nm;
	private String ec_offical_no;
	private String cc_kor_nm;
	private String ec_nm_kr;
	private String ec_classify_no;
	private int rownum;
	private int totalcnt;
}
