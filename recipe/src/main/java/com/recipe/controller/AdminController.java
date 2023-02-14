package com.recipe.controller;

import com.recipe.dto.Notice;
import com.recipe.service.AdminService;
import com.recipe.util.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequestMapping("/admin/")
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("makeMain")
    public String makeMainForm(){
        return "/admin/makeMain";
    }
    @PostMapping("makeMain")
    public String makeMain(Notice notice, MultipartFile pic) throws Exception{
        if(pic.isEmpty()){
            notice.setImage("noPic.jpg");
        } else{
            File file = new File(UUID.randomUUID().toString(), pic.getOriginalFilename(),
                    pic.getContentType());
            java.io.File newFileName = new java.io.File(file.getUuid()+"_"+file.getOriginalName());
            pic.transferTo(newFileName);
            notice.setImage(newFileName.toString());
        }
        adminService.insertNotice(notice);
        return "/admin/adminMain";
    }
}
