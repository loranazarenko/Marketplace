package com.project.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * DTO class for fast serialization and transfer to the Internet
 */
@Slf4j
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Positive
    @Min(value=0)
    @Digits(integer = 10, fraction = 2)
    private double money;
}






