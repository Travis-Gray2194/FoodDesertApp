package me.travisgray.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ${TravisGray} on 2/28/2018.
 */

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String image;

    private String itemName;

    private String servingSize;

    private String itemType;

    @ManyToMany(mappedBy = "items")
    private Set<User> user;

    public Item() {

        this.user = new HashSet<User>();
    }

    public Item(String image, String itemName, String servingSize, String itemType) {
        this.image = image;
        this.itemName = itemName;
        this.servingSize = servingSize;
        this.itemType = itemType;
    }

    public void addUser(User u){
        this.user.add(u);
    }


    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
