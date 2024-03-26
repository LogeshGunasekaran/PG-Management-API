package com.project.pgmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.pgmanagement.model.constant.PgAmenities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pg")
public class Pg {
    @Id
    @GeneratedValue(generator = "sequence_pg")
    @SequenceGenerator(name = "pg_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "pg_name", length = 25, nullable = false)
    private String pgName;
    @ManyToOne
    private Owner owner;
    @Column(length = 15, nullable = false)
    private String contactnumber;
    @Column(length = 30, nullable = false)
    private String location;
    @OneToMany(targetEntity = Room.class)
    @JoinColumn(name = "pg_id", referencedColumnName = "id")
    private List<Room> rooms;
    @Embedded
    private PgAmenities amenities;


    @JsonIgnore
    public Integer getId() {
        return id;
    }
}
