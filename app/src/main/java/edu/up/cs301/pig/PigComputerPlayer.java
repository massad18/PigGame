package edu.up.cs301.pig;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.up.cs301.game.GameComputerPlayer;
import java.util.Random;
import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
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
    PigHoldAction hold;
    PigRollAction roll;

    /**
     * actor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback methods -- game's state has changed
     *
     * @param info
     *      the information (presumably containing the game's state)
     */

    @Override
    protected void receiveInfo(GameInfo info) {
        // TODO  You will implement this method
        if (pigLocalGame.canMove(player1)) {
            if (info instanceof PigGameState) {
                Random rand = new Random();
                int randomNumber = rand.nextInt(2);
                hold = new PigHoldAction(this);
                roll = new PigRollAction(this);
                if (randomNumber == 0) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            game.sendAction(hold);
                        }
                    }, 2000);
                }
                else {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            game.sendAction(roll);
                        }
                    }, 2000);
                }
            }
        }
        else {
            return;
        }
    }//receiveInfo
}