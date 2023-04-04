package com.example.demo.milestones;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Table;
import org.springframework.core.style.ToStringCreator;
import javax.persistence.*;

@Entity
public class Milestones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer id;

    @Column(name = "milestone_name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String milestone_name;

    @Column(name = "milestone_sets")
    @NotFound(action = NotFoundAction.IGNORE)
    private String milestone_sets;

    @Column(name = "milestone_reps")
    @NotFound(action = NotFoundAction.IGNORE)
    private String milestone_reps;

    @Column(name = "milestone_weight")
    @NotFound(action = NotFoundAction.IGNORE)
    private String milestone_weight;

    public Milestones() {

    }

    public Milestones(String milestone_name, String milestone_sets, String milestone_reps) {
        this.milestone_name = milestone_name;
        this.milestone_sets = milestone_sets;
        this.milestone_reps = milestone_reps;
        this.milestone_weight = "N/A";
    }

    public Milestones(String milestone_name, String milestone_sets, String milestone_reps, String milestone_weight) {
        this.milestone_name = milestone_name;
        this.milestone_sets = milestone_sets;
        this.milestone_reps = milestone_reps;
        this.milestone_weight = milestone_weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMilestoneName() {
        return this.milestone_name;
    }

    public void setMilestoneName(String milestone_name) {
        this.milestone_name = milestone_name;
    }

    public String getMilestoneSets() {
        return this.milestone_sets;
    }

    public void setMilestoneSets(String milestone_sets) {
        this.milestone_sets = milestone_sets;
    }

    public String getMilestoneReps() {
        return this.milestone_reps;
    }

    public void setMilestoneReps(String milestone_reps) {
        this.milestone_reps = milestone_reps;
    }

    public String getMilestoneWeight() {
        return this.milestone_weight;
    }

    public void setMilestoneWeight(String milestone_weight) {
        this.milestone_weight = milestone_weight;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("workoutName", this.getMilestoneName())
                .append("workoutSet", this.getMilestoneSets())
                .append("workoutReps", this.getMilestoneReps())
                .append("workoutWeight", this.getMilestoneWeight()).toString();
    }
}