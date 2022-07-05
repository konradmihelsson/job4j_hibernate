package ru.job4j.hibernate.lazy.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "autobrands")
public class AutoBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "autoBrand")
    private List<AutoModel> autoModels = new ArrayList<>();

    public static AutoBrand of(String name) {
        AutoBrand autoBrand = new AutoBrand();
        autoBrand.name = name;
        return autoBrand;
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

    public List<AutoModel> getAutoModels() {
        return autoModels;
    }

    public void setAutoModels(List<AutoModel> autoModels) {
        this.autoModels = autoModels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AutoBrand autoBrand = (AutoBrand) o;
        return id == autoBrand.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AutoBrand{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
