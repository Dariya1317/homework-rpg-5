package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        HeroProfile hero = new HeroProfile("Knight Alibi", 100);
        BossEnemy boss = new BossEnemy("Ancient Shadow Dragon", 150, 12);

        AttackAction basic = new BasicAttack("Sword Slash", 15);

        AttackAction poisonedFireAttack = new FireRuneDecorator(
                new PoisonCoatingDecorator(basic)
        );

        AttackAction ultimateAttack = new CriticalFocusDecorator(poisonedFireAttack);

        System.out.println("--- DECORATOR PATTERN PROOF ---");
        System.out.println("1. Base Attack: " + basic.getActionName() + 
                           " | Damage: " + basic.getDamage());
        
        System.out.println("2. Mid-tier:    " + poisonedFireAttack.getActionName() + 
                           " | Damage: " + poisonedFireAttack.getDamage());
        
        System.out.println("3. Ultimate:    " + ultimateAttack.getActionName());
        System.out.println("   Final Damage: " + ultimateAttack.getDamage());
        System.out.println("   Full Summary: " + ultimateAttack.getEffectSummary());
        System.out.println("-------------------------------\n");

        System.out.println("--- FACADE PATTERN PROOF (Dungeon Run) ---");
    
        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);

        AdventureResult result = facade.runAdventure(hero, boss, ultimateAttack);

        System.out.println("ADVENTURE SUMMARY:");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Final Reward: " + result.getReward());

        System.out.println("FULL BATTLE LOG:");
        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println("\n=== Demo Complete ===");
    }
}
