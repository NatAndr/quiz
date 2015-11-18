package com.getjavajob.training.web06.andrianovan.quiz.model;

/**
 * Created by Nat on 30.10.2015.
 */
public class StudyGroup extends BaseEntity {

    private String groupName;

    public StudyGroup() {
    }

    public StudyGroup(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudyGroup that = (StudyGroup) o;

        if (getId() != that.getId()) return false;
        return !(groupName != null ? !groupName.equals(that.groupName) : that.groupName != null);

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }
}
