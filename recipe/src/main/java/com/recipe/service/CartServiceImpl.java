package com.recipe.service;

import com.recipe.dto.Cart;
import com.recipe.dto.FundingKit;
import com.recipe.dto.Member;
import com.recipe.persistence.CartRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;

    @Override
    public List<Cart> getCartList(Member member) {
        return cartRepo.findAllByMember(member);
    }

    @Override
    public Cart checkMemberKit(Member member, FundingKit mealkit) {
        return cartRepo.findByMemberAndFundingKit(member, mealkit);
    }

    @Override
    public void changeCartQuan(Cart vo) {
        Cart update = cartRepo.findById(vo.getCartId()).get();
        update.setQuantity(vo.getQuantity());
        cartRepo.save(update);
    }

    @Override
    public void deleteCart(Cart vo) {
        cartRepo.deleteById(vo.getCartId());
    }

    @Override
    public Cart getCartById(Long cartId) {
        return cartRepo.findById(cartId).get();
    }

    @Override
    @Transactional
    public void deleteCartByMember(Member member) {
        cartRepo.deleteAllByMember(member);
    }
}
