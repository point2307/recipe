package com.recipe.service;

import com.recipe.dto.Notice;

import java.util.List;

public interface AdminService {

    void insertNotice(Notice notice);

    Notice recipeBanner();
    Notice fundingBanner();
    Notice eventBanner();

    List<Notice> columnList();


}
