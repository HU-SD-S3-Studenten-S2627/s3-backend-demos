package nl.hu.s3.project.booch.bad;

/**
 * SLECHT VOORBEELD: een zak met data.
 * <p>
 * Alle velden zijn private, maar via de setters kan iedereen er alles in zetten.
 * Dit object heeft state en identity, maar geen gedrag. Het kan dus ook geen enkele regel bewaken.
 */
public class TennisGame {

    private Point serverPoint = Point.LOVE;
    private Point receiverPoint = Point.LOVE;
    private boolean finished;
    private Side winner;

    public Point getServerPoint() {
        return serverPoint;
    }

    public void setServerPoint(Point serverPoint) {
        this.serverPoint = serverPoint;
    }

    public Point getReceiverPoint() {
        return receiverPoint;
    }

    public void setReceiverPoint(Point receiverPoint) {
        this.receiverPoint = receiverPoint;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Side getWinner() {
        return winner;
    }

    public void setWinner(Side winner) {
        this.winner = winner;
    }
}
