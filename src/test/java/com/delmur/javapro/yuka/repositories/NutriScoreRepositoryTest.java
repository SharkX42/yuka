package com.delmur.javapro.yuka.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class NutriScoreRepositoryTest {

    @Autowired
    private INutriScoreRepository repository;

    @Test
    void getNutriScoreClassTest() {
        assertEquals(1, repository.getNutriScoreClass(-10).getId());
        assertEquals(1, repository.getNutriScoreClass(-1).getId());
        assertEquals(2, repository.getNutriScoreClass(0).getId());
        assertEquals(3, repository.getNutriScoreClass(8).getId());
        assertEquals(4, repository.getNutriScoreClass(16).getId());
        assertEquals(5, repository.getNutriScoreClass(39).getId());


    }

}
