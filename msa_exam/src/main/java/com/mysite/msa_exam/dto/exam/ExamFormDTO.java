package com.mysite.msa_exam.dto.exam;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class ExamFormDTO {
	
	private int em_cd;
	
	@NotBlank(message = "Exam name은 필수값")
	private String em_nm;
	
	//@NotBlank(message = "em_nm_kr은 필수값")
	private String em_nm_kr;
	
	private String em_view_yn;
	
	private String mode;
	private int page = 1;
}
