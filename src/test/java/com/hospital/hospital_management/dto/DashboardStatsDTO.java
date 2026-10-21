package com.hospital.hospital_management.dto;

public class DashboardStatsDTO {

    private long todayAppointments;
    private long pendingAppointments;
    private long completedAppointments;
    private long pendingFollowUps;

    public DashboardStatsDTO() {}

    public DashboardStatsDTO(long todayAppointments, long pendingAppointments, long completedAppointments, long pendingFollowUps) {
        this.todayAppointments = todayAppointments;
        this.pendingAppointments = pendingAppointments;
        this.completedAppointments = completedAppointments;
        this.pendingFollowUps = pendingFollowUps;
    }

    public long getTodayAppointments() { return todayAppointments; }
    public void setTodayAppointments(long todayAppointments) { this.todayAppointments = todayAppointments; }

    public long getPendingAppointments() { return pendingAppointments; }
    public void setPendingAppointments(long pendingAppointments) { this.pendingAppointments = pendingAppointments; }

    public long getCompletedAppointments() { return completedAppointments; }
    public void setCompletedAppointments(long completedAppointments) { this.completedAppointments = completedAppointments; }

    public long getPendingFollowUps() { return pendingFollowUps; }
    public void setPendingFollowUps(long pendingFollowUps) { this.pendingFollowUps = pendingFollowUps; }
}