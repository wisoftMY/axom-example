package com.cqrs.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class WithDrawMoneyEvent {
    private String holderID;
    private String accountID;
    private Long amount;
}
