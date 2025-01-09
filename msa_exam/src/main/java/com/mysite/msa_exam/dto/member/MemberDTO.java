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
public class MemberDTO {
	private String mm_id;
	private String mm_pwd;
	private String mm_mem_nm;
	private String mm_number;
	private String mm_ent_dt;
	private String mm_sex;
	private String md_mem_grd;
	private String md_post_no;
	private String md_addr1;
	private String md_adddr2;
	private String md_email;
	private String md_hp_no;
	private String md_tel_no;
	private int mm_dom_cd;
	private String s_std_sdt;
	private String d_std_sdt;
	private String std_sdt;
	private String mm_mem_type;
	private String mm_mem_srnm;
	private String md_school;
	private String md_grade;
	private String md_st_tp;
	private String md_atd_tp;
	private String ma_id;
	private String md_spg_tp;
	private String mt_tcas_yr;
	private int mdc_total;
	private int rownum;
	private int totalcnt;
	
	
}
