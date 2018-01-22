package com.example.desenvolvedor2015.invatedmanager.entities;

/**
 * Created by desenvolvedor on 22/01/18.
 */

public class GuestCount {
    private int allInvatedCount;
    private int absentCount;
    private int presentCount;

    public GuestCount(int allInvatedCount, int absentCount, int presentCount) {
        this.allInvatedCount = allInvatedCount;
        this.absentCount = absentCount;
        this.presentCount = presentCount;
    }

    public int getAllInvatedCount() {
        return allInvatedCount;
    }

    public void setAllInvatedCount(int allInvatedCount) {
        this.allInvatedCount = allInvatedCount;
    }

    public int getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(int absentCount) {
        this.absentCount = absentCount;
    }

    public int getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(int presentCount) {
        this.presentCount = presentCount;
    }
}
