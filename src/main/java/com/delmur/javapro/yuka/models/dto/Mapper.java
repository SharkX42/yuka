package com.delmur.javapro.yuka.models.dto;

import com.delmur.javapro.yuka.models.BasketAverage;
import com.delmur.javapro.yuka.models.ProductResult;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

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

    //***********************************************************************************//

    public BasketDTO toDto(@NotNull BasketAverage basketAverage) {
        ArrayList<ProductDTO> listProductsDTO= new ArrayList<ProductDTO>();
        for(ProductResult.Product p : basketAverage.getProducts())
        {
            listProductsDTO.add(toDto(p));
        }

        return BasketDTO.builder()
                .average(basketAverage.getAverageNutriScore())
                .classe(basketAverage.getNutriScoreClass().getClasse())
                .color(basketAverage.getNutriScoreClass().getColor())
                .products(listProductsDTO)
                .build();
    }

}

