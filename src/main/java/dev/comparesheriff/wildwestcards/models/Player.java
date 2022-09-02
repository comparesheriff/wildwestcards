package dev.comparesheriff.wildwestcards.models;

public class Player {
    private final String id;
    private final String handle;
    private final boolean human;

    private final boolean host;

    public Player(final String id, final String handle, final boolean human, final boolean host) {
        this.id = id;
        this.handle = handle;
        this.human = human;
        this.host = host;
    }

    public boolean isHuman() {
        return human;
    }

    public String getHandle() {
        return handle;
    }

    public String getId() {
        return id;
    }

    public boolean isHost() {
        return host;
    }
}
