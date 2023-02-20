package com.recipe.util;

import com.recipe.dto.Funding;
import com.recipe.persistence.FundingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Scheduler {

    @Autowired
    private FundingRepo fundingRepo;

    @Scheduled(cron = "0 59 23 * * *")
    public void fundingCheck(){
        Date today = new Date();
        String pro = "펀딩 진행 중";
        List<Funding> list = fundingRepo.findByProcess(pro);
        for(Funding fund : list){
            if(fund.getFinalDate().after(today)){
                if(Math.floor(fund.getFund()/(double)fund.getGoal()) < 100){
                    fund.setProcess("펀딩 실패");
                } else{
                    fund.setProcess("펀딩 성공");
                }
            }
        }
    }
}
