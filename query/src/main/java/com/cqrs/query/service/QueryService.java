package com.cqrs.query.service;

import com.cqrs.events.query.loan.LoanLimitResult;
import com.cqrs.query.entity.HolderAccountSummary;
import reactor.core.publisher.Flux;

import java.util.List;

public interface QueryService {
    void reset();
    HolderAccountSummary getAccountInfo(String holderId);
    Flux<HolderAccountSummary> getAccountInfoSubscription(String holderId);
    List<LoanLimitResult> getAccountInfoScatterGather(String holderId);
}
