package com.server.scarlet_shade.utils.enumerator;

public enum PhaseName {
    
    CHAPTER_ONE_PHASE_ONE("Disaster");

    private final String namePhase;

    PhaseName(String namePhase) {
        this.namePhase = namePhase;
    }

    public String getNamePhase() {
        return namePhase;
    }
}
