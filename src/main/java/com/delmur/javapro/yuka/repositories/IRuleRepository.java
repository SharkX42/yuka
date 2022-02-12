package com.delmur.javapro.yuka.repositories;

import com.delmur.javapro.yuka.models.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRuleRepository extends JpaRepository<Rule, Integer> {
    @Query("Select max(points) from Rule where name = 'energy_100g' AND min_bound < :quantity")
    int getEnergyScore(@Param("quantity") double quantity);

    @Query("Select max(points) from Rule where name = 'saturated-fat_100g' AND min_bound < :quantity")
    int getSaturatedFlatScore(@Param("quantity") double quantity);

    @Query("Select max(points) from Rule where name = 'sugars_100g' AND min_bound < :quantity")
    int getSugarScore(@Param("quantity") double quantity);

    @Query("Select max(points) from Rule where name = 'salt_100g' AND min_bound < :quantity")
    int getSaltScore(@Param("quantity") double quantity);

    @Query("Select max(points) from Rule where name = 'fiber_100g' AND min_bound < :quantity")
    int getFiberScore(@Param("quantity") double quantity);

    @Query("Select max(points) from Rule where name = 'proteins_100g' AND min_bound < :quantity")
    int getProteinScore(@Param("quantity") double quantity);
}
