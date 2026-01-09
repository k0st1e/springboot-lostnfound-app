package com.example.lostnfoundrevamped.repositories;

import com.example.lostnfoundrevamped.entities.ItemEntity;
import com.example.lostnfoundrevamped.entities.UserEntity;
import com.example.lostnfoundrevamped.entities.dto.CategoriesDto;
import com.example.lostnfoundrevamped.entities.enums.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

    // Create a new dto for each one.
    // From all the items `i` group them by the `category name`.
    @Query("""
    SELECT new com.example.lostnfoundrevamped.entities.dto.CategoriesDto(
        i.categoryEntity.categoryname,
        COUNT(i)
    )
    FROM ItemEntity i
    GROUP BY i.categoryEntity.categoryname
    """)
    // This goes to the service and then in the Dash Controller,
    // it is used to make a list of `CategoriesDto` that we pass,
    // to the model as `items`.
    List<CategoriesDto> countItemsByCategory();


    // FROM ItemEntity i -> Look for all the items.
    // Select (distinct) user who has that item.
    // `i` is the item. So include items that match with `:category`.
    // AND operator, match also with the `:item status`.
    @Query("""
    SELECT DISTINCT i.userEntity FROM ItemEntity i
    WHERE i.categoryEntity.categoryid = :category
    AND i.itemstatus = :itemstatus
    """)
    // Put `category` and `item status` that we pass in the Service.
    List<UserEntity> findUsersToNotify(@Param("category") Integer category,
                                       @Param("itemstatus") ItemStatus itemstatus);
}