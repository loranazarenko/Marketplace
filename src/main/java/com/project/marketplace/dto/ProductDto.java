package com.project.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.marketplace.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

/**
 * DTO class for fast serialization and transfer to the Internet
 */
@Slf4j
@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotEmpty
    public String name;

    @Positive
    @Min(value=0)
    @Digits(integer = 10, fraction = 2)
    public double price;

    public User user;
}
