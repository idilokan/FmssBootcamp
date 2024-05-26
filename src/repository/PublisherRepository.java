package repository;

import model.Publisher;

import java.util.ArrayList;
import java.util.List;

public class PublisherRepository {
    private static PublisherRepository instance;
    private List<Publisher> publishers;

    private PublisherRepository() {
        publishers = new ArrayList<>();
    }

    public static PublisherRepository getInstance() {
        if (instance == null) {
            instance = new PublisherRepository();
        }
        return instance;
    }

    public void save(Publisher publisher) {
        publishers.add(publisher);
    }

    public List<Publisher> findAll() {
        return publishers;
    }
}
