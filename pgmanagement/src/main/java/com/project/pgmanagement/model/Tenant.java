package com.project.pgmanagement.model;

import com.project.pgmanagement.model.constant.Account;
import com.project.pgmanagement.model.constant.Authority;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "tenant" , uniqueConstraints =
                        @UniqueConstraint(columnNames = {"username", "password", "mail"}))
public class Tenant {

    @Id
    @GeneratedValue(generator = "sequence_tenant")
    @SequenceGenerator(name = "tenant_seq", allocationSize = 1)
    private Integer id;
    @OneToOne(optional = false , cascade = CascadeType.ALL)
    private  Account account;
    @Column(length = 40, nullable = false)
    private String mail;
    @CreationTimestamp
    private LocalTime joinDate;
    @ManyToOne
    @JoinColumn(name = "room_no")

    private Room room;

    public void setAccount(@Valid Account account)
    {
        account.setAuthority(Authority.USER.name());
        this.account = account;
    }
  

}
