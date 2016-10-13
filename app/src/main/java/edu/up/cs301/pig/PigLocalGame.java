package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

import java.util.Random;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    PigGameState pigGame;
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        //TODO  You will implement this constructor
        pigGame = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        //TODO  You will implement this method
        if (pigGame.getPlayerTurn() == playerIdx) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        //TODO  You will implement this method
        for (int i = 0; i < 2; i ++) {
            if (canMove(i)) {
                if (action instanceof PigRollAction) {
                    Random rand = new Random();
                    int val = rand.nextInt(6) + 1;
                    pigGame.setDieValue(val);
                    if (val == 1) {
                        pigGame.setRunningTotal(val);
                        pigGame.setPlayerTurn(i);
                    } else {
                        pigGame.setRunningTotal(val);
                    }
                }
                if (action instanceof PigHoldAction) {
                    int total = pigGame.getRunningTotal();
                    if (i == 0) {
                        pigGame.setPlayer0Score(total);
                    }
                    else {
                        pigGame.setPlayer1Score(total);
                    }
                    pigGame.setRunningTotal(1);
                }
                return true;
            }
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
        PigGameState temp = new PigGameState(pigGame);
        p.sendInfo(temp);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO  You will implement this method
        if (pigGame.getPlayer0Score() >= 50) {
            return ""+playerNames[0]+" has won the game with a score of "+pigGame.getPlayer0Score();
        }
        else if (pigGame.getPlayer1Score() >= 50) {
            return ""+playerNames[1]+" has won the game with a score of "+pigGame.getPlayer0Score();
        }
        else {
            return null;
        }
    }

}// class PigLocalGame
