package com.application.practiceVer8.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.application.practiceVer8.dto.BoardDTO;



@Mapper
public interface BoardDAO {

	 public void createBoard(BoardDTO boardDTO);

	public List<BoardDTO> getBoardList();

	public BoardDTO getBoardDetail(long boardId);

	public String getEncodedPasswd(long boardId);

	public void updateReadCnt(long boardId);

	public void updateBoard(BoardDTO boardDTO);

	public void deleteBoard(long boardId);

}
