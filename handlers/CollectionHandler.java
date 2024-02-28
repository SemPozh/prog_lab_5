package handlers;

import models.Organization;

import java.time.ZonedDateTime;
import java.util.Collection;

public class CollectionHandler {
    private Collection<Organization> collection;
    private final String collectionType;
    private final ZonedDateTime initializationDate;

    public CollectionHandler(Collection<Organization> obj){
        this.collection = obj;
        this.collectionType = obj.getClass().getTypeName();
        this.initializationDate = ZonedDateTime.now();
    }

    public ZonedDateTime getInitializationDate() {
        return initializationDate;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public Collection<Organization> getCollection() {
        return collection;
    }

    public void addElements(Collection<Organization> collection) {
        this.collection.addAll(collection);
    }


}
