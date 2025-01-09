package com.mysite.msa_exam.dto.subject;

import com.mysite.msa_exam.dto.exam.ExamFormDTO;

import groovy.transform.ToString;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class SubjectFormDTO {
	

	@NotBlank(message = "subcd 필수값")
	private String subcd;	
	
	@NotBlank(message = "subname 필수값")
	private String subnm;

	private String old_subcd;
	
	private String subvw;
	private int sub_tg = 4;
	private String subkbn = "1";
	
	private String method;
	private int page = 1;
}
