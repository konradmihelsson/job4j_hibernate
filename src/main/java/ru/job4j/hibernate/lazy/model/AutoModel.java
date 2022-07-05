package ru.job4j.hibernate.lazy.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "automodels")
public class AutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "autobrand_id")
    private AutoBrand autoBrand;

    public static AutoModel of(String name, AutoBrand autoBrand) {
        AutoModel autoModel = new AutoModel();
        autoModel.name = name;
        autoModel.autoBrand = autoBrand;
        return autoModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AutoBrand getAutoBrand() {
        return autoBrand;
    }

    public void setAutoBrand(AutoBrand autoBrand) {
        this.autoBrand = autoBrand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AutoModel autoModel = (AutoModel) o;
        return id == autoModel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AutoModel{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
