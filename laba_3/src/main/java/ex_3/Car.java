package ex_3;

import java.util.List;
import java.util.Objects;

public class Car {
    private String model;
    private Type type;
    private Long year;
    private Long mileage;
    private List<Driver> drivers;

    public Car() {
    }

    public Car(String model, Type type, Long year, Long mileage, List<Driver> drivers) {
        this.model = model;
        this.type = type;
        this.year = year;
        this.mileage = mileage;
        this.drivers = drivers;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(model, car.model) &&
                type == car.type &&
                Objects.equals(year, car.year) &&
                Objects.equals(mileage, car.mileage) &&
                Objects.equals(drivers, car.drivers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(model, type, year, mileage, drivers);
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", type=" + type +
                ", year=" + year +
                ", mileage=" + mileage +
                ", drivers=" + drivers +
                '}';
    }
}
