package com.loversQuest.gameWorldPieces.models_NPC;

import com.loversQuest.gameWorldPieces.NonPlayerCharacters;
import com.loversQuest.gameWorldPieces.Player;

public class DrillSGT_PT extends NonPlayerCharacters {
    public DrillSGT_PT(String name, String description) {
        super(name, description);
    }

    @Override
    public String interact(Player player) {
        String returnMsg = "do Push ups";
        /**
         * TODO: write DrillPT_A_LOT interact, get award if PT >270
         */
        return returnMsg;
    }



}