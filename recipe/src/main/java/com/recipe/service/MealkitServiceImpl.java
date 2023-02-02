package com.recipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.persistence.MealkitRepo;

@Service
public class MealkitServiceImpl implements MealkitService {

	@Autowired
	private MealkitRepo kitRepo;
	
	
}
