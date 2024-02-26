package com.application.practiceVer8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.application.practiceVer8.dto.BoardDTO;
import com.application.practiceVer8.service.BoardService;
//2024-02-25 총 소요 시간: 1시간
// 변경한 사항: authentication에서 패스워드 불일치시 boardList 가 아닌 boardDetail 로 넘겨주었다.
//고려상항: boardDetail은 boardId는 매개변수로 넘겨주어야한다.

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/createBoard")
	public String createBoard() {
		return "board/createBoard";
	}
	
	@PostMapping("/createBoard")
	@ResponseBody
	public String createBoard(@ModelAttribute BoardDTO boardDTO) {
		//System.out.println(boardDTO);
		boardService.createBoard(boardDTO);
		
		String scriptString="""
				<script>
				alert('게시글이 등록되었습니다.');
				location.href='/board/boardList';
				</script>
				""";
		
		return scriptString;
	}
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
		return "board/boardList";
	}

	@GetMapping("/boardDetail")
	public String boardDetial(Model model, @RequestParam("boardId") long boardId) {
		model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
		return "board/boardDetail";

	}
	
	@GetMapping("/authentication")
	public String authentication(Model model, @RequestParam("boardId") long boardId, @RequestParam("menu") String menu) {
		model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
		model.addAttribute("menu", menu);
		
		return "board/authentication";
	}
	
	@PostMapping("/authentication")
	@ResponseBody
	public String authentication(@ModelAttribute BoardDTO boardDTO, @RequestParam("menu") String menu) {
		 boolean isAuthenticated = boardService.isAuthenticated(boardDTO);
		 String scriptString = "";
		 if(isAuthenticated) {
			if(menu.equals("update")) {
				scriptString = "<script>";
				scriptString += "alert('인증되었습니다!');";
				scriptString += "location.href='/board/updateBoard?boardId="+boardDTO.getBoardId()+"';";
				scriptString += "</script>";
			}
			else if (menu.equals("delete")) {
				scriptString = "<script>";
				scriptString += "alert('인증되었습니다!');";
				scriptString += "location.href='/board/deleteBoard?boardId="+boardDTO.getBoardId()+"';";
				scriptString += "</script>";
			}
		 }
		 else {
			 scriptString = "<script>";
				scriptString += "alert('패스워드를 재확인하세요!');";
				scriptString += "location.href='/board/boardDetail?boardId="+boardDTO.getBoardId()+"';";
				scriptString += "</script>";
		}
		return scriptString;
	}
	
	@GetMapping("/updateBoard")
	public String updateBoard(Model model , @RequestParam("boardId") long boardId) {
		model.addAttribute("boardDTO", boardService.getBoardDetail(boardId));
		return "board/updateBoard";
	}
	
	@PostMapping("/updateBoard")
	@ResponseBody
	public String updateBoard(@ModelAttribute BoardDTO boardDTO) {
		boardService.updateBoard(boardDTO);
		
		String scriptString="""
				<script>
				alert('게시글이 수정되었습니다.');
				location.href='/board/boardList';
				</script>
				""";
		
		return scriptString;
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Model model, @RequestParam("boardId") long boardId) {
		model.addAttribute("boardId", boardId);
		return "board/deleteBoard";
	}
	
	@PostMapping("deleteBoard")
	@ResponseBody
	public String deleteBoard(@RequestParam("boardId") long boardId) {
		boardService.deleteBoard(boardId);
		String scriptString="""
				<script>
				alert('게시글이 삭제되었습니다.');
				location.href='/board/boardList';
				</script>
				""";
		
		return scriptString;
		
	}
	
	
	

}
