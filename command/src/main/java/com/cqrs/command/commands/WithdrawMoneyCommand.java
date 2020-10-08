package com.cqrs.command.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@ToString
@Getter
public class WithdrawMoneyCommand {
    @TargetAggregateIdentifier
    private final String accountID;
    private final String holderID;
    private final Long amount;
}

