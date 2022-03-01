package com.ai13qcm.entities;

import javax.persistence.*;

@Entity
@Table(name="theme")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String label;

    public Theme() {
        this.id = -1;
    }

    public Theme(Integer id, String label){
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
