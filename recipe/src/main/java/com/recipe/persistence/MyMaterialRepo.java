package com.recipe.persistence;

import com.recipe.dto.MyMaterial;
import org.springframework.data.repository.CrudRepository;
import com.recipe.dto.Member;
import java.util.List;

public interface MyMaterialRepo extends CrudRepository<MyMaterial, Long> {
    List<MyMaterial> findByMember(Member member);
}
