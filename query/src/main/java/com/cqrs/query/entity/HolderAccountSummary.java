package com.cqrs.query.entity;

import lombok.*;


import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "MV_ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class HolderAccountSummary {
    @Id
    @Column(name = "holder_id", nullable = false)
    private String holderId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String tel;
    @Column(nullable = false)
    private String address;
    @Column(name = "total_balance", nullable = false)
    private Long totalBalance;
    @Column(name = "account_cnt", nullable = false)
    private Long accountCnt;
}
