package com.lfdev.crudspring.domain.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseProductDTO {
    String id;
    String name;

    Integer price_in_cents;


}
