package com.recipe.service;

import com.recipe.dto.Notice;
import com.recipe.persistence.NoticeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private NoticeRepo noticeRepo;


    @Override
    public void insertNotice(Notice notice) {
        noticeRepo.save(notice);
    }
}
