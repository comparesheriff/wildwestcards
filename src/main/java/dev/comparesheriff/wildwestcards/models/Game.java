package dev.comparesheriff.wildwestcards.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Game {
    private final int MAX_PLAYERS = 7;
    private final int MIN_PLAYERS = 4;
    private final String id;
    private final boolean extension;
    private final boolean showPublic;
    private final AISpeed aiSpeed;
    private final List<Player> players = new ArrayList<>();
    private boolean hasStarted = false;

    private int turnNumber = 0;


    public Game(final String id, final boolean extension, final boolean showPublic, final Player player, final AISpeed aiSpeed) {
        this.id = id;
        this.extension = extension;
        this.showPublic = showPublic;
        this.aiSpeed = aiSpeed;
        players.add(player);
    }

    public boolean isExtension() {
        return extension;
    }

    public boolean isShowPublic() {
        return showPublic;
    }

    public AISpeed getAiSpeed() {
        return aiSpeed;
    }

    public boolean addPlayer(final Player player) {
        if (player != null && players.size() < MAX_PLAYERS && getPlayerWithHandle(player.getHandle()).isEmpty() && !hasStarted) {
            players.add(player);
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isFull() {
        return players.size() == MAX_PLAYERS;
    }

    public boolean getHasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public boolean canStart() { return !hasStarted && players.size() >= MIN_PLAYERS;}

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Game) obj;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Game[" +
                "id=" + id +
                ", extension=" + extension +
                ", isPublic=" + showPublic +
                ", aiSpeed=" + aiSpeed +
                ", isStarted=" + hasStarted +
                ", players=" + players +
                ']';
    }
    public boolean hasPlayerWithHandle(final String playerHandle) {
        return players.stream().anyMatch(currentPlayer -> currentPlayer.getHandle().equals(playerHandle));
    }

    public Optional<Player> getPlayerWithHandle(String playerHandle) {
        return players.stream().filter(currentPlayer -> currentPlayer.getHandle().equals(playerHandle)).findFirst();
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void nextTurn() {
        this.turnNumber += 1;
    }

    public void start() {
        setHasStarted(true);
        nextTurn();
    }
}
