package com.cmdgame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class myTests {

    @Test
    void testInputName() {
        CmdgameApplication game = new CmdgameApplication();
        game.run();
    }

    @Test
    void testRandomizeEnemies() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(10) + 1;
        CmdgameApplication game = new CmdgameApplication();
        ArrayList<Enemy> t = game.randomlyGenerateEnemies(randomNumber);
        assertEquals(randomNumber, t.size());
    }

    @Test
    void testSaveGame() {

    }

    @Test
    void testLoadGame() {

    }
}