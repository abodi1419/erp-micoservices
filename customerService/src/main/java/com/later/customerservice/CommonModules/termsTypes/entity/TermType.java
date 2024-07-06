package com.later.customerservice.CommonModules.termsTypes.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class TermType {
    private Long id;
    private String termName;
    private String termNameAr;
}
