package com.recipe.controller;

import com.recipe.dto.Board;
import com.recipe.security.SecurityUser;
import com.recipe.service.BoardServiceImpl;
import com.recipe.util.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
public class BoardController {

    @Autowired
    private BoardServiceImpl boardService;

    @GetMapping("/common/boardList")
    public String getBoardList(Model model, @PageableDefault(sort = "boardId" ,direction = Sort.Direction.DESC) Pageable paging){
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
    public String insertBoard(Board vo, @AuthenticationPrincipal SecurityUser principal,
                              @RequestParam(value ="image", required = false)MultipartFile image)
    throws Exception{
        if(vo.getBoardKind().equals("300")){
            if(image.isEmpty()){
                vo.setBoardImage("noImg.jpg");
            } else{
                File file = new File(UUID.randomUUID().toString(), image.getOriginalFilename(),
                        image.getContentType());
                java.io.File newFileName = new java.io.File(file.getUuid()+"_"+file.getOriginalName());
                image.transferTo(newFileName);
                vo.setBoardImage(newFileName.toString());
            }
        }

        vo.setBoardWriter(principal.getMember());
        boardService.insertBoard(vo);
        return "redirect:/common/getBoard?boardId="+vo.getBoardId();
    }
    @GetMapping("/common/getBoard")
    public String getBoard(Board vo, Model model){
        model.addAttribute("Board", boardService.getBoardById(vo));
        return "/common/getBoard";
    }
}