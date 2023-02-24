package com.recipe.service;

public interface ReplyService {
    void deleteReply(Long id);

    void updateReply(Long id, String content);
}
