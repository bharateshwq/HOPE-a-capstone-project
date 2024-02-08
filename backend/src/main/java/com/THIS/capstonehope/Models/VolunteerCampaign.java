package com.THIS.capstonehope.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "volunteer_campaigns")
public class VolunteerCampaign {
    @Id
    private String id;
    private String title;
    private String description;
    private String location;
    private String timings;
    private int maxVolunteers;

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

    public String getLocation() {
        return location;
    }

    public String getTimings() {
        return timings;
    }

    public int getMaxVolunteers() {
        return maxVolunteers;
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

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public void setMaxVolunteers(int maxVolunteers) {
        this.maxVolunteers = maxVolunteers;
    }
}
