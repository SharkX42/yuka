package com.delmur.javapro.yuka.repositories;

import com.delmur.javapro.yuka.models.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRuleRepository extends JpaRepository<Rule, Integer> {
    @Query("Select rule from Rule rule where name= :name and points = (Select max(points) from Rule where name = :name AND min_bound < :quantity)")
    Rule getScoreFromNutrientName(@Param("name") String name, @Param("quantity") double quantity);
}
