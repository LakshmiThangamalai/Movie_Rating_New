package com.movie.ratings.model;

public class CustomerRating {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private Double customerAverageRating;
    private Double averageRating;

    public CustomerRating() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getCustomerAverageRating() {
        return customerAverageRating;
    }

    public void setCustomerAverageRating(Double customerAverageRating) {
        this.customerAverageRating = customerAverageRating;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
