package com.crud.kodillalibrary.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Loans {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "dateOfLoan")
    private LocalDate dateOfLoan;

    @Column(name = "dateOfReturn")
    private LocalDate dateOfReturn;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "copyId")
    private Copy copy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "readerId")
    private Reader reader;

}
