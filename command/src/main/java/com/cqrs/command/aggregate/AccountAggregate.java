package com.cqrs.command.aggregate;

import com.cqrs.command.commands.AccountCreationCommand;
import com.cqrs.command.commands.DepositMoneyCommand;
import com.cqrs.command.commands.WithdrawMoneyCommand;
import com.cqrs.events.AccountCreationEvent;
import com.cqrs.events.DepositMoneyEvent;
import com.cqrs.events.WithDrawMoneyEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@RequiredArgsConstructor
@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String accountID;
    private String holderID;
    private Long balance;

    @CommandHandler
    public AccountAggregate(AccountCreationCommand command) {
        log.debug("handling {}", command);
        apply(new AccountCreationEvent(command.getHolderID(), command.getAccountID()));
    }

    @EventSourcingHandler
    public void createAccount(AccountCreationEvent event) {
        log.debug("applying {}", event);
        this.accountID = event.getAccountID();
        this.holderID = event.getHolderID();
        this.balance = 0L;
    }

    @CommandHandler
    public void depositMoney(DepositMoneyCommand command) {
        log.debug("handling {}", command);
        if(command.getAmount() <= 0) throw  new IllegalStateException("amount >= 0");
        apply(new DepositMoneyEvent(command.getHolderID(), command.getAccountID(), command.getAmount()));
    }

    @EventSourcingHandler
    public void depositMoney(DepositMoneyEvent event) {
        log.debug("applying {}", event);
        this.balance +=event.getAmount();
        log.debug("balance {}", this.balance);
    }

    @CommandHandler
    public void withdrawMoney(WithdrawMoneyCommand command){
        log.debug("handling {}", command);
        if(this.balance - command.getAmount() < 0) throw new IllegalStateException("잔고가 부족합니다.");
        else if(command.getAmount() <= 0 ) throw new IllegalStateException("amount >= 0");
        apply(new WithDrawMoneyEvent(command.getHolderID(), command.getAccountID(), command.getAmount()));
    }
    @EventSourcingHandler
    protected void withdrawMoney(WithDrawMoneyEvent event){
        log.debug("applying {}", event);
        this.balance -= event.getAmount();
        log.debug("balance {}", this.balance);
    }
}
