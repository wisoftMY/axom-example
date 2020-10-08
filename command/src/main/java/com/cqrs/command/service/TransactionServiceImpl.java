package com.cqrs.command.service;

import com.cqrs.command.commands.AccountCreationCommand;
import com.cqrs.command.commands.DepositMoneyCommand;
import com.cqrs.command.commands.HolderCreationCommand;
import com.cqrs.command.commands.WithdrawMoneyCommand;
import com.cqrs.command.dto.AccountDTO;
import com.cqrs.command.dto.DepositDTO;
import com.cqrs.command.dto.HolderDTO;
import com.cqrs.command.dto.WithdrawalDTO;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final CommandGateway commandGateway;

    @Override
    public CompletableFuture<String> createHolder(HolderDTO holderDTO) {
        return commandGateway.send(new HolderCreationCommand(UUID.randomUUID().toString()
        , holderDTO.getHolderName()
        , holderDTO.getTel()
        , holderDTO.getAddress()
        , holderDTO.getCompany()));
    }

    @Override
    public CompletableFuture<String> createAccount(AccountDTO accountDTO) {
        return commandGateway.send(new AccountCreationCommand(accountDTO.getHolderID(), UUID.randomUUID().toString()));
    }

    @Override
    public CompletableFuture<String> depositMoney(DepositDTO transactionDTO) {
        System.out.println(transactionDTO.getAccountID());
        return commandGateway.send(new DepositMoneyCommand(transactionDTO.getAccountID(), transactionDTO.getHolderID(), transactionDTO.getAmount()));
    }

    @Override
    public CompletableFuture<String> withdrawMoney(WithdrawalDTO transactionDTO) {
        return commandGateway.send(new WithdrawMoneyCommand(transactionDTO.getAccountID(), transactionDTO.getHolderID(), transactionDTO.getAmount()));
    }
}
