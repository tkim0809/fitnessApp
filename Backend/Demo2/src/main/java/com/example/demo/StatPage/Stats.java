package com.example.demo.StatPage;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Table;
import org.springframework.core.style.ToStringCreator;
import javax.persistence.*;

@Entity
public class Stats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer id;

    @Column(name = "workout_name")
    @NotFound(action = NotFoundAction.IGNORE)
    private String workout_name;

    @Column(name = "workout_sets")
    @NotFound(action = NotFoundAction.IGNORE)
    private String workout_sets;

    @Column(name = "workout_reps")
    @NotFound(action = NotFoundAction.IGNORE)
    private String workout_reps;

    @Column(name = "workout_weight")
    @NotFound(action = NotFoundAction.IGNORE)
    private String workout_weight;

    public Stats() {

    }

    public Stats(String workout_name, String workout_sets, String workout_reps) {
        this.workout_name = workout_name;
        this.workout_sets = workout_sets;
        this.workout_reps = workout_reps;
        this.workout_weight = "N/A";
    }

    public Stats(String workout_name, String workout_sets, String workout_reps, String workout_weight) {
        this.workout_name = workout_name;
        this.workout_sets = workout_sets;
        this.workout_reps = workout_reps;
        this.workout_weight = workout_weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkoutName() {
        return this.workout_name;
    }

    public void setWorkoutName(String workout_name) {
        this.workout_name = workout_name;
    }

    public String getWorkoutSets() {
        return this.workout_sets;
    }

    public void setWorkoutSets(String workout_sets) {
        this.workout_sets = workout_sets;
    }

    public String getWorkoutReps() {
        return this.workout_reps;
    }

    public void setWorkoutReps(String workout_reps) {
        this.workout_reps = workout_reps;
    }

    public String getWorkoutWeight() {
        return this.workout_weight;
    }

    public void setWorkoutWeight(String workout_weight) {
        this.workout_weight = workout_weight;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("workoutName", this.getWorkoutName())
                .append("workoutSet", this.getWorkoutSets())
                .append("workoutReps", this.getWorkoutReps())
                .append("workoutWeight", this.getWorkoutWeight()).toString();
    }
}
