package org.generation.SkillBarter.enums;

public enum ScheduledStatus {
    PENDING,      // proposed but not yet confirmed
    CONFIRMED,    // accepted by both users
    REJECTED,     // declined by either user
    COMPLETED;    // session has happened successfully

}

