package com.recipe.service;

import com.recipe.dto.*;

import java.util.List;

public interface MyPageService {

    FundingKit findFundingkit(Funding funding, Mealkit mealkit);

    void saveCart(Cart vo);

    List<Material> listMater(Long data);

    void insertMater(Member mem, String data);

    List<MyMaterial> mymaterList(Member member);

    void deleteMyMater(Long Id);
}
