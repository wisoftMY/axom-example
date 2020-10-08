package com.cqrs.jeju.component;

import com.cqrs.events.query.loan.LoanLimitQuery;
import com.cqrs.events.query.loan.LoanLimitResult;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountLoanComponent {

    @QueryHandler
    private LoanLimitResult on(LoanLimitQuery query) {
        log.debug("handling {}",query);
        return LoanLimitResult.builder()
                .holderID(query.getHolderID())
                .balance(query.getBalance())
                .bankName("JejuBank")
                .loanLimit(Double.valueOf(query.getBalance() * 1.2).longValue())
                .build();
    }
}
