package com.application.practiceVer8.service;

import java.util.List;

import com.application.practiceVer8.dto.BoardDTO;

public interface BoardService {

	public void createBoard(BoardDTO boardDTO);

	public List<BoardDTO> getBoardList();

	public BoardDTO getBoardDetail(long boardId);

	public boolean isAuthenticated(BoardDTO boardDTO);

	public void updateBoard(BoardDTO boardDTO);

	public void deleteBoard(long boardId);

}
	