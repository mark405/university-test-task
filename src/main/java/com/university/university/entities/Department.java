package com.university.university.entities;

import com.university.university.models.WorkerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@Getter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "departments", fetch = FetchType.EAGER)
    private Set<Worker> workers = new HashSet<>();

    public Worker getHead() {
        return workers.stream().filter(it -> it.getWorkerType() == WorkerType.HEAD_OF_DEPARTMENT).findAny().orElseThrow();
    }
}