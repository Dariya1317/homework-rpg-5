package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;
import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        int round = 0;
        int maxRounds = 10;

        result.addLine("Battle Starts: " + hero.getName() + " vs " + boss.getName());

        while (hero.isAlive() && boss.isAlive() && round < maxRounds) {
            round++;
            result.addLine("Round " + round + ":");
            int heroDamage = action.getDamage();
            boss.takeDamage(heroDamage);
            result.addLine("  > " + hero.getName() + " uses " + action.getActionName() + 
                           " for " + heroDamage + " damage " +
                           boss.getName() + " HP: " + boss.getHealth());
            if (!boss.isAlive()) {
                result.setWinner(hero.getName());
                result.addLine(boss.getName() + " has been defeated");
                break;
            }
            int bossDamage = boss.getAttackPower();
            hero.takeDamage(bossDamage);
            result.addLine("  < " + boss.getName() + " strikes back for " + bossDamage + 
                           " damage. " + hero.getName() + " HP: " + hero.getHealth());
            if (!hero.isAlive()) {
                result.setWinner(boss.getName());
                result.addLine(hero.getName() + " has fallen in battle");
                break;
            }
        }
        if (result.getWinner() == null) {
            result.setWinner("Draw");
            result.addLine("The battle lasted too long. It's a draw");
        }

        result.setRounds(round);
        return result;
    }
}
