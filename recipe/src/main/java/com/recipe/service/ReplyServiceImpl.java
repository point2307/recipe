package com.recipe.service;

import com.recipe.persistence.ReplyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepo replyRepo;


    @Override
    public void deleteReply(Long id) {
        replyRepo.deleteById(id);
    }
}
