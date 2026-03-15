package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        // TODO: Decide reward rules based on battle outcome.
        //if (battleResult == null) {
           // return "TODO";
        //}
        //return "TODO";
        if (battleResult == null || battleResult.getWinner() == null) {
            return "no reward (unknown outcome)";
        }

        String winner = battleResult.getWinner();
        if (winner.equals("Draw")) {
            return "small pouch of gold (participation prize)";
        } else if (winner.contains("Boss") || winner.equals("TODO Boss")) {
            return "no reward (hero was defeated)";
        } else {
            return "legendary dragon scale and 500 gold";
        }
    }

}
