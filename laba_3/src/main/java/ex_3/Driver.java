package ex_3;

import java.util.Objects;

public class Driver {
    private String name;
    private Long age;
    private Long experience;

    public Driver(String name, Long age, Long experience) {
        this.name = name;
        this.age = age;
        this.experience = experience;
    }

    public Driver() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(name, driver.name) &&
                Objects.equals(age, driver.age) &&
                Objects.equals(experience, driver.experience);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, experience);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", experience=" + experience +
                '}';
    }
}
