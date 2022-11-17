package model;

// 1.	Фамилия — поиск покупателей с этой фамилией

public class CriterionOne{
    public CriterionOne(String lastName) {
        this.lastName = lastName;
    }

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "CriterionOne{" +
                "lastName='" + lastName + '\'' +
                '}';
    }
}
