package com.recipe.service;

import com.recipe.dto.Notice;

public interface AdminService {

    void insertNotice(Notice notice);

    Notice recipeBanner();
    Notice fundingBanner();
    Notice eventBanner();


}
