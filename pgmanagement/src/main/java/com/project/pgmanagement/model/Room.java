package com.project.pgmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
//@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "room", uniqueConstraints = @UniqueConstraint(columnNames = "roomno"))
public class Room {

    @Id
    @GeneratedValue(generator = "sequence_room")
    @SequenceGenerator(name = "room_seq", allocationSize = 1)
    private Integer id;
    @Column
    private Integer roomno;
    @Column(length = 10)
    private String floor;
    @Column(length = 50, nullable = false)
    private Integer sharing;
    @Column(length = 50, nullable = false)
    private Integer rent;
    @Column(length = 5)
    private Integer vacancy;
    @ManyToOne
    private Pg pg;
    @OneToMany(targetEntity = Tenant.class)
    @JoinColumn(name = "room_no", referencedColumnName = "roomno")
    private List<Tenant> tenants;

    @JsonIgnore
    public Pg getPg() {
        return pg;
    }

    @JsonIgnore
    public List<Tenant> getTenants() {
        return tenants;
    }
}
