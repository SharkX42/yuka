package com.delmur.javapro.yuka.repositories;

import com.delmur.javapro.yuka.models.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Integer> {
    @Query("Select max(points) from rule where name = 'energy_100g' AND min_bound < :quantity;")
    int getEnergyScore(@Param("quantity") int quantity);
}
