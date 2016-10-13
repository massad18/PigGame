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
public class PigComputerPlayer extends GameComputerPlayer {

    PigLocalGame pigLocalGame = new PigLocalGame();
    int player1 = playerNum;
    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
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
                Random rand = new Random();
                int randomNumber = rand.nextInt(2);
                if (randomNumber == 0) {
                    PigHoldAction hold = new PigHoldAction(this);
                    game.sendAction(hold);
                    ((PigGameState) info).setPlayerTurn(player1);
                } else {
                    PigRollAction roll = new PigRollAction(this);
                    game.sendAction(roll);
                }
            }
        }
        else {
            return;
        }
    }//receiveInfo

}
