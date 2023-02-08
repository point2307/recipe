package com.recipe.service;

import com.recipe.dto.Funding;
import com.recipe.dto.Mealkit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.recipe.persistence.MealkitRepo;

import java.util.List;

@Service
public class MealkitServiceImpl implements MealkitService {

	@Autowired
	private MealkitRepo kitRepo;


	@Override
	public void insertKit(Mealkit vo) {
		kitRepo.save(vo);
	}

	@Override
	public void updateKit(Mealkit vo) {

	}

	@Override
	public void deleteKit(Mealkit vo) {
		kitRepo.delete(vo);
	}

	@Override
	public Mealkit getKit(Mealkit vo) {
		return kitRepo.findById(vo.getKitId()).get();
	}

	@Override
	public Page<Mealkit> getKitList(Pageable pageable) {
		return kitRepo.getMealkitList(pageable);
	}

	@Override
	public Page<Mealkit> getKitByFunding(Pageable pageable, Funding vo) {
		return null;
	}

	@Override
	public Mealkit getKitforFunding(String str){
		return kitRepo.findBykitName(str);
	}
}
