package com.crud.kodillalibrary.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Readers")
public class Reader {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "accountCreationDate")
    private LocalDate accountCreationDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (
            name = "joinLoansReaders",
            joinColumns = {@JoinColumn(name = "readerId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "loanId", referencedColumnName = "id")}
    )
    private List<Loans> loans;
}
