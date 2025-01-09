package com.mysite.msa_exam.dto.exam;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class ExamCateFormDTO {
	
	private int ec_em_cd;	
	private int ec_cd;
	
	@NotBlank(message = "category name은 필수값")
	private String ec_nm;

	private String ec_nm_kr;
	
	private String ec_part_nm;
	
	private String ec_offical_no;
	
	private String ec_classify_no;
	
	private String mode;
	private int page = 1;
}
