package com.cqrs.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransactionDTO {
    private String accountID;
    private String holderID;
    private Long amount;
}
