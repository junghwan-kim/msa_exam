package com.mysite.msa_exam;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Pagination {
	private int pageSize = 10; //
	private int blockSize = 10;
	private int page = 1;
	private int block = 1;
	private int totalListCnt;
	private int totalPageCnt;
	private int totalBlockCnt;
	private int startPage= 1;
	private int endPage = 1;
	private int startIndex = 0;
	private int preBlock;
	private int nextBlock;
	private int prev;
	private int next;
	
	public void pageInfo(int totalCnt, int page) {
		/*
		int start = ((page-1) / blockLimit) * blockLimit + 1;					
		int end = ((start + pageSize - 1) < totalPage) ? start + pageSize - 1 : totalPage;
		*/
		setPage(page);
		setTotalListCnt(totalCnt);
		if(totalCnt == 0) {
			setEndPage(1);
			setPage(1);
			setTotalPageCnt(1);
			return;
		}
		
		//총 페이지 수
		setTotalPageCnt((int) Math.ceil((double)(totalCnt) / pageSize));
		if(totalCnt <= pageSize) {
			setTotalPageCnt(1);
		}
		
		//총 블럭 수
		setTotalBlockCnt((int) Math.ceil((double)(totalPageCnt) / blockSize));
		//현재 블럭
		setBlock((int) Math.ceil((double)(page) / blockSize));
		//블럭 시작페이지
		setStartPage((block - 1) * blockSize + 1);
		//블럭 마지막페이지
		//setEndPage(startPage + blockSize + 1);
		//블럭 마지막 페이지는 무조건 정해져 있지 않으므로
		//if(totalPageCnt < endPage) this.endPage = totalPageCnt;
		setEndPage(((startPage + blockSize - 1) < totalPageCnt) ? startPage + blockSize - 1 : totalPageCnt);
		
		//이전 블럭(클릭 시 이전 블록의 마지막페이지를 가져옴)
		setPreBlock(((block-1) * blockSize));
		//1에서 이전 블록을 누르면 오류
		if(preBlock < 1) this.preBlock = 1;
		//다음 블럭 (클릭 시 다음블럭 첫번째 페이지)
		setNextBlock((block*blockSize) + 1);
		//다음 블럭 눌렀을시 전체 페이지수가 끝났을때
		if(nextBlock > totalPageCnt) this.nextBlock = totalPageCnt;
		
		
		//이전페이지
		if(page != 1) setPrev(page-1);
		//다음페이지
		if(page < totalPageCnt) setNext(page+1);
		
		setStartIndex((page=1) * pageSize);		
	}

	
}
