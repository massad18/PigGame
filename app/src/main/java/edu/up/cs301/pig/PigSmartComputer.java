package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigSmartComputer extends GameComputerPlayer {

    PigLocalGame pigLocalGame = new PigLocalGame();
    PigGameState pigGame = new PigGameState();
    int player1 = playerNum;
    int curVal;
    int curTotal;
    int curScore;
    int oppScore;
    PigRollAction roll;
    PigHoldAction hold;
    /**
     * ctor does nothing extra
     */
    public PigSmartComputer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // TODO  You will implement this method
        if (pigLocalGame.canMove(player1)) {
            if (info instanceof PigGameState) {
                hold = new PigHoldAction(this);
                roll = new PigRollAction(this);
                if (player1 == 0) {
                    curScore = ((PigGameState) info).getPlayer1Score();
                    oppScore = ((PigGameState) info).getPlayer0Score();
                    Choice(curScore,oppScore);
                }
                else {
                    curScore = ((PigGameState) info).getPlayer0Score();
                    oppScore = ((PigGameState) info).getPlayer1Score();
                    Choice(curScore,oppScore);
                }
                curTotal = ((PigGameState) info).getRunningTotal();
                curVal = ((PigGameState) info).getDieValue();
            }
        }
        else {
            return;
        }
    }//receiveInfo

    // Method to determine how much to shoot for until holding depending
    // on how far the opponent is from winning and the difference in the
    // two scores
    protected void Choice (int curScore, int oppScore) {
        if (curScore + curTotal >= 50) {
            game.sendAction(hold);
        }
        // roll until you get at least 6 when 6 away
        else if (50 - (curScore + curTotal) <= 6) {
            Move(6);
        }
        // be more risky when your opponent is winning by a lot
        else if (oppScore - curScore >= 35) {
            Move(30,27);
        }
        // play safer when not losing by a lot and less than 20 away
        else if (50 - (curScore + curTotal) <= 20) {
            Move(12);
        }
        // play it by the books when even with your opponent
        else {
            Move(20);
        }
    }

    // Move method for losing
    protected void Move (int Max, int atLeast) {
        if (50 - oppScore <= 6) {
            if (curTotal >= Max || curScore + curTotal == 50) {
                game.sendAction(hold);
            }
            else {
                game.sendAction(roll);
            }
        }
        else {
            if (curTotal < atLeast) {
                game.sendAction(roll);
            }
            else {
                game.sendAction(hold);
            }
        }
    }

    // Move method for tied or winning
    protected void Move (int atLeast) {
        if (50 - oppScore <= 6) {
            if (curScore + curTotal == 50) {
                game.sendAction(hold);
            }
            else {
                game.sendAction(roll);
            }
        }
        else {
            if (curTotal < atLeast) {
                game.sendAction(roll);
            }
            else {
                game.sendAction(hold);
            }
        }
    }
}
