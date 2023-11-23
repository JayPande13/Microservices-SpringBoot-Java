package com.example.demo.services.dto;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponseDto<T> {
    private Integer statusCode;
    private String message;


//    private List<ErrorMessage> errorMessages= new ArrayList<>();
//    @Builder.Default
    private T content ;

}
