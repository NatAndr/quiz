package com.getjavajob.training.web06.andrianovan.quiz.model;

import javax.persistence.*;

/**
 * Created by Nat on 30.10.2015.
 */
@Entity
@Table(name = "study_group")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = "StudyGroup.getAll", query = "SELECT s FROM StudyGroup s")
public class StudyGroup extends BaseEntity {

    @Column(name = "group_name", nullable = false)
    private String name;

    public StudyGroup() {
    }

    public StudyGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudyGroup that = (StudyGroup) o;

        if (getId() != that.getId()) return false;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
