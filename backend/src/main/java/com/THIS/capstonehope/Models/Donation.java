package com.THIS.capstonehope.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "donations")
public class Donation {
    @Id
    private String id;
    private String title;
    private String description;
    private double requiredAmount;
    private String image;
    private String contactDetails;
    private String email;

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getRequiredAmount() {
        return requiredAmount;
    }

    public String getImage() {
        return image;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequiredAmount(double requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
