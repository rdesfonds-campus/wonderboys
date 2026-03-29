package game;

import characters.Character;

public class Main {

    public static void main(String[] args) {

        characters.Character hero = new characters.Character("Warrior", "Thor", 10, 5);

        System.out.println(hero);
    }
}