package com.mysite.msa_exam.dto.member;

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
public class DailyCheckDTO {
	private String mdc_reg_dt;
	private int rownum;
	private int totalcnt;
}
