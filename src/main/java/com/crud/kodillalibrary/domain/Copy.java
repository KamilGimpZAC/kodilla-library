package com.crud.kodillalibrary.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Copies")
public class Copy {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CopyStatus status;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "titleId")
    private Title title;

    @OneToMany (
            targetEntity = Loans.class,
            mappedBy = "copy",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Loans> loans;
}
