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

    @Scheduled(cron = "0 1 0 * * *")
    public void fundingCheck(){
        Date today = new Date();
        System.out.println("스케줄러 실행중..");
        String pro = "펀딩 진행 중";
        List<Funding> list = fundingRepo.findByProcess(pro);
        System.out.println(list);
        for(Funding fund : list){
            if(fund.getFinalDate().before(today)){
                System.out.println(fund);
                if(Math.floor(fund.getFund()/(double)fund.getGoal()) < 100){
                    fund.setProcess("펀딩 실패");
                    System.out.println(fund);
                    fundingRepo.save(fund);
                } else{
                    fund.setProcess("펀딩 성공");
                    fundingRepo.save(fund);
                }

            }
        }
    }
}
