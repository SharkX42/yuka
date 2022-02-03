package com.delmur.javapro.yuka.repositories;

import com.delmur.javapro.yuka.models.NutriScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface INutriScoreRepository extends JpaRepository<NutriScore, Integer> {
    @Query("Select n from NutriScore n where n.lower_bound <= :score AND :score <= n.upper_bound")
    NutriScore getNutriScoreClass(@Param("score") int score);
}
