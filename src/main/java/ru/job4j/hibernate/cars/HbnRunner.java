package ru.job4j.hibernate.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.cars.model.Make;
import ru.job4j.hibernate.cars.model.Model;

public class HbnRunner {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Model model1 = Model.of("Prius");
            session.save(model1);
            Model model2 = Model.of("Sequoia");
            session.save(model2);
            Model model3 = Model.of("Mark II");
            session.save(model3);
            Model model4 = Model.of("Avensis");
            session.save(model4);
            Model model5 = Model.of("Rav4");
            session.save(model5);

            Make make = Make.of("Toyota");
            make.addModel(session.load(Model.class, 1));

            session.save(make);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}