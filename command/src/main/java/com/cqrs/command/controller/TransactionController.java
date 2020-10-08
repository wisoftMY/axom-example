package com.cqrs.command.controller;

import com.cqrs.command.dto.AccountDTO;
import com.cqrs.command.dto.DepositDTO;
import com.cqrs.command.dto.HolderDTO;
import com.cqrs.command.dto.WithdrawalDTO;
import com.cqrs.command.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/holder")
    public CompletableFuture<String> createHolder(@RequestBody HolderDTO holderDTO) {
        return transactionService.createHolder(holderDTO);
    }

    @PostMapping("/account")
    public CompletableFuture<String> createAccount(@RequestBody AccountDTO accountDTO) {
        return transactionService.createAccount(accountDTO);
    }

    @PostMapping("/deposit")
    public CompletableFuture<String> deposit(@RequestBody DepositDTO transactionDTO) {
        System.out.println(transactionDTO.getAccountID());
        return transactionService.depositMoney(transactionDTO);
    }

    @PostMapping("/withdrawal")
    public CompletableFuture<String> withdraw(@RequestBody WithdrawalDTO transactionDTO) {
        return transactionService.withdrawMoney(transactionDTO);
    }
}
