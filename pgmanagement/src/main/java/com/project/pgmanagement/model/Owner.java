package com.project.pgmanagement.model;

import com.project.pgmanagement.model.constant.Account;
import com.project.pgmanagement.model.constant.Authority;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "ownerdetails",
        uniqueConstraints = {@UniqueConstraint( name = "unique_key",columnNames = {"mail" , "password"})})
public class Owner {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Setter
    @Column(length = 30 , nullable = false)
    private String mail;
    @OneToOne(optional = false  , cascade = CascadeType.ALL)
    private  Account account;
    @Setter
    @OneToMany(targetEntity = Pg.class )
    @JoinColumn(name = "owner_id" , referencedColumnName = "id")
    private List<Pg> pgList;

    public void setAccount(Account account)
    {
        account.setAuthority(Authority.ADMIN.name());
        this.account = account;
    }


}
