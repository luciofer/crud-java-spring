package com.lfdev.crudspring.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


public record RequestProductDTO(
        String id,

        String name,

        Integer price_in_cents) {
}
