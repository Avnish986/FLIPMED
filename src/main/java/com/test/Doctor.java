package com.test;

import java.util.List;

public class Doctor extends Person{
    private Speciality speciality;
    private double rating;
    private List<String> availability;

    public Doctor() {

    }
    public Doctor(Speciality speciality, double rating, List<String> availability) {
        this.speciality = speciality;
        this.rating = rating;
        this.availability = availability;
    }

    public String getSpeciality() {
        return speciality.toString();
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getAvailability() {
        return availability;
    }

    public void setAvailability(List<String> availability) {
        this.availability = availability;
    }
}
