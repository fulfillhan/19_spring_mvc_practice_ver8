package com.application.practiceVer8.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {
	private long boardId;
	private String passwd;
	private String writer;
	private String subject;
	private String content;
	private long readCnt;
	private Date enrollAt;
	
}
