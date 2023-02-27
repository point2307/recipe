package com.recipe.service;

import com.querydsl.core.BooleanBuilder;
import com.recipe.dto.*;
import com.recipe.persistence.CartRepo;
import com.recipe.persistence.FundingKitRepo;
import com.recipe.persistence.MaterialRepo;
import com.recipe.persistence.MyMaterialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageServiceImpl implements MyPageService {

    @Autowired
    private MaterialRepo materialRepo;
    @Autowired
    private FundingKitRepo fundingKitRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private MyMaterialRepo myMaterialRepo;

    @Override
    public FundingKit findFundingkit(Funding funding, Mealkit mealkit) {

        return fundingKitRepo.findByFundingAndMealkit(funding, mealkit);
    }

    @Override
    public void saveCart(Cart vo) {
        cartRepo.save(vo);
    }

    @Override
    public List<Material> listMater(Long data) {
        return materialRepo.findByMatIdStartingWith(data);
    }

    @Override
    public void insertMater(Member mem, String data) {
        System.out.println(data);
        Material mater = materialRepo.findById(data).get();
        MyMaterial myMater = new MyMaterial();
        myMater.setMember(mem);
        myMater.setMaterial(mater);

        myMaterialRepo.save(myMater);
    }

    @Override
    public List<MyMaterial> mymaterList(Member member) {
        return myMaterialRepo.findByMember(member);
    }

    @Override
    public void deleteMyMater(Long Id) {
        myMaterialRepo.deleteById(Id);
    }
}
