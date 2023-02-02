package com.recipe.controller;

import com.recipe.dto.Board;
import com.recipe.dto.Member;
import com.recipe.security.SecurityUser;
import com.recipe.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board/")
@Controller
public class BoardController {

    @Autowired
    private BoardServiceImpl boardService;

    @GetMapping("boardList")
    public String getBoardList(Model model){
        Page<Board> list = boardService.getBoardList();
        model.addAttribute("boardList", list);
        return "/common/boardList";
    }

    @GetMapping("insertBoardForm")
    public String insertBoardForm(){
        return "/board/insertBoard";
    }

    @PostMapping("insertBoard")
    public String insertBoard(Board vo, @AuthenticationPrincipal SecurityUser principal){
        vo.setBoard_writer(principal.getMember());
        boardService.insertBoard(vo);
        return "redirect:/board/boardList";
    }
    @GetMapping("getBoard")
    public String getBoard(Board vo, Model model){
        model.addAttribute("Board", boardService.getBoardById(vo));
        return "/common/getBoard";
    }
}