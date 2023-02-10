package com.recipe;

import com.recipe.dto.*;
import com.recipe.persistence.FundingKitRepo;
import com.recipe.persistence.MemberRepo;
import com.recipe.service.BoardService;
import com.recipe.service.BoardServiceImpl;
import com.recipe.service.MemberServiceImpl;
import com.recipe.service.MyPageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardControllerTest {
    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private MyPageService myPageService;

    @Autowired
    FundingKitRepo fundingKitRepo;

    @Test
    public void sear(){
        Funding funding = new Funding();
        funding.setFundId(9L);
        Mealkit mealkit = new Mealkit();
        mealkit.setKitId(6L);

        System.out.println(myPageService.findFundingkit(funding, mealkit));
    }
}
