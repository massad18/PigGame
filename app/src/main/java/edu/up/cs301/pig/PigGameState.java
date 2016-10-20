package edu.up.cs301.pig;

import android.widget.TextView;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by massad18 on 10/12/2016.
 */
public class PigGameState extends GameState {
    int playerTurn;
    int player0Score;
    int player1Score;
    int runningTotal;
    int dieValue;
    PigLocalGame pigLocalGame;

    GameMainActivity myActivity;
    GameHumanPlayer gameHumanPlayer;
    TextView playerNameTextView;
    TextView oppNameTextView;

    PigGameState() {
        super();
    }

    PigGameState(PigGameState p) {
        super();
        playerTurn = p.getPlayerTurn();
        player0Score = p.getPlayer0Score();
        player1Score = p.getPlayer1Score();
        runningTotal = p.getRunningTotal();
        dieValue = p.getDieValue();
    }

    int getPlayerTurn() {
        return playerTurn;
    }

    int getPlayer0Score() {
        return player0Score;
    }

    int getPlayer1Score() {
        return player1Score;
    }

    int getRunningTotal() {
        return runningTotal;
    }

    int getDieValue() {
        return dieValue;
    }

    void setPlayerTurn(int curPlayer) {
        playerTurn = 1 - curPlayer;
    }

    void setPlayer0Score(int score) {
        player0Score += score;
    }

    void setPlayer1Score(int score) {
        player1Score += score;
    }

    void setRunningTotal(int total) {
        if(total == 1) {
            runningTotal = 0;
        }
        else {
            runningTotal = runningTotal + total;
        }
    }

    void setDieValue(int value) {
        dieValue = value;
    }
}
