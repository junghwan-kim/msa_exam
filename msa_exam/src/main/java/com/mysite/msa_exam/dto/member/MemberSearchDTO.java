package com.mysite.msa_exam.dto.member;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.Data;

@Data
public class MemberSearchDTO {
	private String startDt;
	private String endDt;
	private String sch_dchk;
	private int smode;
	private String keyword;
	
	private Date today = new Date();
	
	public void constructDt() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentTime = dateFormat.format(today);
		
		this.startDt = AddDate(currentTime, 0,-6,0);
		this.endDt = currentTime;
		
	}
	
	private static String AddDate(String strDate, int year, int month, int day) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		Date dt = dateFormat.parse(strDate);
		cal.setTime(dt);
		cal.add(Calendar.YEAR, year);
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.DATE, day);
		return dateFormat.format(cal.getTime());
	}
}
