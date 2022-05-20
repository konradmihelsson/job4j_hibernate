package ru.job4j.hibernate.books;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hibernate.books.model.Author;
import ru.job4j.hibernate.books.model.Book;

import java.util.Arrays;

public class HbnRunner {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book first = Book.of("Book for Java programming");
            Book second = Book.of("Book about Hibernate");
            Book third = Book.of("Book about design patterns");
            Book fourth = Book.of("Book about something other");

            Author authorFor123Book = Author.of("Author who written first, second and third books");
            authorFor123Book.getBooks().addAll(Arrays.asList(first, second, third));

            Author authorFor13Book = Author.of("Author who written first and third books");
            authorFor13Book.getBooks().addAll(Arrays.asList(first, third));

            Author authorFor12Book = Author.of("Author who written first and second books");
            authorFor12Book.getBooks().addAll(Arrays.asList(first, second));

            Author authorFor34Book = Author.of("Author who written third and fourth books");
            authorFor34Book.getBooks().addAll(Arrays.asList(third, fourth));

            Author authorFor4Book = Author.of("Author who written fourth book");
            authorFor4Book.getBooks().add(fourth);

            session.persist(authorFor123Book);
            session.persist(authorFor13Book);
            session.persist(authorFor12Book);
            session.persist(authorFor34Book);
            session.persist(authorFor4Book);

            session.getTransaction().commit();

            /*
                authorFor12Book will be be removed
             */
            session.beginTransaction();
            Author author = session.get(Author.class, 3);
            session.remove(author);
            session.getTransaction().commit();

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
