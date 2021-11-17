package entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class User {
    @Getter @Setter private int id;
    @Getter @Setter private String fio;
    @Getter @Setter private String address;
    @Getter @Setter private String telephoneNumber;
    @Getter @Setter private String salary;

    public User(String fio, String address, String telephoneNumber, String salary) {
        this.fio = fio;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(fio, user.fio) && Objects.equals(address, user.address) && Objects.equals(telephoneNumber, user.telephoneNumber) && Objects.equals(salary, user.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, address, telephoneNumber, salary);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", address='" + address + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
