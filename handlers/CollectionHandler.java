package handlers;

import models.Organization;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Stack;

public class CollectionHandler {
    private final Collection<Organization> collection;
    private final String collectionType;

    private final FileHandler fileHandler;
    private final ZonedDateTime initializationDate;

    public CollectionHandler(Collection<Organization> obj, FileHandler fileHandler){
        this.collection = obj;
        this.collectionType = obj.getClass().getTypeName();
        this.initializationDate = ZonedDateTime.now();
        this.fileHandler = fileHandler;
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

    public void addElement(Organization el){
        collection.add(el);
    }

    public Organization getElementById(int id){
        for (Organization org: collection){
            if (org.getId() == id){
                return org;
            }
        }
        throw new NoSuchElementException("There are no organization with this ID");
    }

    public void removeElement(Organization organization){
        collection.remove(organization);
    }

    public void removeAll(){
        collection.clear();
    }

    public void insertAt(Organization organization, int index){
        ((Stack<Organization>) collection).insertElementAt(organization, index);
    }

    public int getCollectionSize(){
        return collection.size();
    }

    public Organization getMax(){
        return Collections.max(collection);
    }

    public void reverseCollection(){
        Collections.reverse((Stack<Organization>) collection);
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }
}
