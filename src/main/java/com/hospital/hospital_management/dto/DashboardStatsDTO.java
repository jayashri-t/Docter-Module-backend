package com.hospital.hospital_management.dto;

public class DashboardStatsDTO {

    private long todayAppointments;
    private long pendingAppointments;
    private long completedAppointments;
    private long pendingFollowUps;
    private long totalFollowups;
    private long emergencyCount;

    public DashboardStatsDTO() {}

    public DashboardStatsDTO(long todayAppointments, long pendingAppointments, 
                             long completedAppointments, long pendingFollowUps, 
                             long totalFollowups, long emergencyCount) {
        this.todayAppointments = todayAppointments;
        this.pendingAppointments = pendingAppointments;
        this.completedAppointments = completedAppointments;
        this.pendingFollowUps = pendingFollowUps;
        this.totalFollowups = totalFollowups;
        this.emergencyCount = emergencyCount;
    }

    public long getTodayAppointments() { return todayAppointments; }
    public void setTodayAppointments(long todayAppointments) { this.todayAppointments = todayAppointments; }

    public long getPendingAppointments() { return pendingAppointments; }
    public void setPendingAppointments(long pendingAppointments) { this.pendingAppointments = pendingAppointments; }

    public long getCompletedAppointments() { return completedAppointments; }
    public void setCompletedAppointments(long completedAppointments) { this.completedAppointments = completedAppointments; }

    public long getPendingFollowUps() { return pendingFollowUps; }
    public void setPendingFollowUps(long pendingFollowUps) { this.pendingFollowUps = pendingFollowUps; }

    public long getTotalFollowups() { return totalFollowups; }
    public void setTotalFollowups(long totalFollowups) { this.totalFollowups = totalFollowups; }

    public long getEmergencyCount() { return emergencyCount; }
    public void setEmergencyCount(long emergencyCount) { this.emergencyCount = emergencyCount; }
}