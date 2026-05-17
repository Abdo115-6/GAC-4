package com.test.gac_4.dto;

public class StatsDTO {
    private long totalActions;
    private long activeUsers;
    private double totalDonations;
    private long myParticipations;

    public StatsDTO() {}

    public StatsDTO(long totalActions, long activeUsers, double totalDonations, long myParticipations) {
        this.totalActions = totalActions;
        this.activeUsers = activeUsers;
        this.totalDonations = totalDonations;
        this.myParticipations = myParticipations;
    }

    // Getters and setters
    public long getTotalActions() {
        return totalActions;
    }

    public void setTotalActions(long totalActions) {
        this.totalActions = totalActions;
    }

    public long getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(long activeUsers) {
        this.activeUsers = activeUsers;
    }

    public double getTotalDonations() {
        return totalDonations;
    }

    public void setTotalDonations(double totalDonations) {
        this.totalDonations = totalDonations;
    }

    public long getMyParticipations() {
        return myParticipations;
    }

    public void setMyParticipations(long myParticipations) {
        this.myParticipations = myParticipations;
    }
}
