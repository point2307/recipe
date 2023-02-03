package com.recipe.controller;

import com.recipe.dto.Board;
import com.recipe.dto.Member;
import com.recipe.security.SecurityUser;
import com.recipe.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardServiceImpl boardService;

    @GetMapping("/common/boardList")
    public String getBoardList(Model model, Pageable paging){
        Page<Board> list = boardService.getBoardList(paging);
        System.out.println(list);

        model.addAttribute("boardList", list);

        return "/common/boardList";
    }

    @GetMapping("/board/insertBoardForm")
    public String insertBoardForm(){
        return "/board/insertBoard";
    }

    @PostMapping("/board/insertBoard")
    public String insertBoard(Board vo, @AuthenticationPrincipal SecurityUser principal){
        vo.setBoard_writer(principal.getMember());
        boardService.insertBoard(vo);
        return "redirect:/common/getBoard?board_id="+vo.getBoard_id();
    }
    @GetMapping("/common/getBoard")
    public String getBoard(Board vo, Model model){
        model.addAttribute("Board", boardService.getBoardById(vo));
        return "/common/getBoard";
    }
}