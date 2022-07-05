package ru.job4j.hibernate.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.lazy.model.AutoBrand;
import ru.job4j.hibernate.lazy.model.AutoModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HbnRunner {

    public static void main(String[] args) {
        List<AutoBrand> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

/*
            This code block was used for create and save data to DB
*/
/*
            AutoBrand autoBrand1 = AutoBrand.of("Toyota");
            AutoModel autoModel1 = AutoModel.of("Prius", autoBrand1);
            session.save(autoModel1);
            AutoModel autoModel2 = AutoModel.of("Sequoia", autoBrand1);
            session.save(autoModel2);
            AutoModel autoModel3 = AutoModel.of("Mark II", autoBrand1);
            session.save(autoModel3);
            AutoModel autoModel4 = AutoModel.of("Avensis", autoBrand1);
            session.save(autoModel4);
            AutoModel autoModel5 = AutoModel.of("Rav4", autoBrand1);
            session.save(autoModel5);
            autoBrand1.setAutoModels(Arrays.asList(autoModel1, autoModel2, autoModel3, autoModel4, autoModel5));
            session.save(autoBrand1);
*/

            list = session.createQuery(
                    "select distinct ab from AutoBrand ab join fetch ab.autoModels"
            ).list();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (AutoBrand autoBrand : list) {
            for (AutoModel autoModel : autoBrand.getAutoModels()) {
                System.out.println(autoModel);
            }
        }
    }
}
