package nl.hu.s3.project.booch.bad;

/**
 * SLECHT VOORBEELD: de spelregels wonen buiten het object waar ze over gaan.
 * <p>
 * De regels kloppen alleen als iedereen via deze service gaat. Niets dwingt dat af.
 */
public class TennisService {

    public void pointTo(TennisGame game, Side side) {
        if (game.isFinished()) {
            throw new IllegalStateException("game is already finished");
        }

        Point own = side == Side.SERVER ? game.getServerPoint() : game.getReceiverPoint();
        Point other = side == Side.SERVER ? game.getReceiverPoint() : game.getServerPoint();

        if (own == Point.ADVANTAGE || (own == Point.FORTY && other.ordinal() < Point.FORTY.ordinal())) {
            game.setFinished(true);
            game.setWinner(side);
        } else if (other == Point.ADVANTAGE) {
            // Terug naar deuce
            setPoint(game, side == Side.SERVER ? Side.RECEIVER : Side.SERVER, Point.FORTY);
        } else {
            setPoint(game, side, Point.values()[own.ordinal() + 1]);
        }
    }

    public String score(TennisGame game) {
        if (game.isFinished()) {
            return "Game " + name(game.getWinner());
        }
        if (game.getServerPoint() == Point.ADVANTAGE) {
            return "Advantage server";
        }
        if (game.getReceiverPoint() == Point.ADVANTAGE) {
            return "Advantage receiver";
        }
        if (game.getServerPoint() == Point.FORTY && game.getReceiverPoint() == Point.FORTY) {
            return "Deuce";
        }
        return text(game.getServerPoint()) + "-" + text(game.getReceiverPoint());
    }

    private void setPoint(TennisGame game, Side side, Point point) {
        if (side == Side.SERVER) {
            game.setServerPoint(point);
        } else {
            game.setReceiverPoint(point);
        }
    }

    private String name(Side side) {
        return side == Side.SERVER ? "server" : "receiver";
    }

    private String text(Point point) {
        return switch (point) {
            case LOVE -> "0";
            case FIFTEEN -> "15";
            case THIRTY -> "30";
            case FORTY -> "40";
            case ADVANTAGE -> "AD";
        };
    }
}
