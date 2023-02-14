package com.recipe.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recipe.dto.Notice;
import com.recipe.persistence.NoticeRepo;
import jakarta.persistence.EntityManager;
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

    @Override
    public Notice recipeBanner() {
        return noticeRepo.findFirstByKindOrderByRegDate(700);
    }

    @Override
    public Notice fundingBanner() {
        return noticeRepo.findFirstByKindOrderByRegDate(800);
    }

    @Override
    public Notice eventBanner() {
        return noticeRepo.findFirstByKindOrderByRegDate(900);
    }

}
