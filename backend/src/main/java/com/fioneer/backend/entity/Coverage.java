package com.fioneer.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="coverage")
public class Coverage {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable=false)
    private String name;

    @Column
    private BigDecimal sumInsured;

    @ManyToOne(optional = false)
    @JoinColumn(name = "class_of_business_id")
    private ClassOfBusiness classOfBusiness;

    @ManyToOne(optional = false)
    @JoinColumn(name="accumulation_id", nullable=false)
    private Accumulation accumulation;

    public Coverage() {
    }

    public Coverage(Long id, String name, BigDecimal sumInsured, ClassOfBusiness classOfBusiness, Accumulation accumulation) {
        this.id = id;
        this.name = name;
        this.sumInsured = sumInsured;
        this.classOfBusiness = classOfBusiness;
        this.accumulation = accumulation;
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

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
    }

    public ClassOfBusiness getClassOfBusiness() {
        return classOfBusiness;
    }

    public void setClassOfBusiness(ClassOfBusiness classOfBusiness) {
        this.classOfBusiness = classOfBusiness;
    }

    public Accumulation getAccumulation() {
        return accumulation;
    }

    public void setAccumulation(Accumulation accumulation) {
        this.accumulation = accumulation;
    }
}
