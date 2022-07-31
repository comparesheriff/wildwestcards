package dev.comparesheriff.wildwestcards.models;

public class Player {
    private final String id;
    private final String handle;
    private final boolean isHuman;

    public Player(final String id, final String handle, final boolean isHuman) {
        this.id = id;
        this.handle = handle;
        this.isHuman = isHuman;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public String getHandle() {
        return handle;
    }

    public String getId() {
        return id;
    }
}
