package com.delmur.javapro.yuka.models.dto;

import com.delmur.javapro.yuka.models.ProductResult;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    //***********************************************************************************//

    public ProductDTO toDto(@NotNull ProductResult.Product product) {

        return ProductDTO.builder()
                .Id(Long.parseLong(product.getCode()))
                .BarCode(product.getCode())
                .Name(product.getGeneric_name() + " - " + product.getProduct_name())
                .NutriScore(product.getNutriScore())
                .Classe(product.getNutriScoreClass().getClasse())
                .Color(product.getNutriScoreClass().getColor())
                .build();
    }

//    public Product toEntity(@NotNull ProductDTO productDTO) {
//
//    }
}

