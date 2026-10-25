package com.fioneer.backend.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="accumulation")
public class Accumulation {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable=false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "class_of_business_id")
    private ClassOfBusiness classOfBusiness;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    private String description;

    @OneToMany(mappedBy = "accumulation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coverage> coverages = new ArrayList<>();

    public Accumulation() {
    }

    public Accumulation(String name, ClassOfBusiness classOfBusiness, Status status, String description) {
        this.name = name;
        this.classOfBusiness = classOfBusiness;
        this.status = status;
        if (description.isEmpty() ||  description == null) {
            this.description = "";
        } else {
            this.description = description;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassOfBusiness getClassOfBusiness() {
        return classOfBusiness;
    }

    public void setClassOfBusiness(ClassOfBusiness classOfBusiness) {
        this.classOfBusiness = classOfBusiness;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addCoverage(Coverage coverage) {
        this.coverages.add(coverage);
    }

    public void removeCoverage(Coverage coverage) {
        this.coverages.remove(coverage);
    }

    public List<Coverage> getCoverages() {
        return coverages;
    }

}
