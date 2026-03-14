package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.Character;

public class RoomCell extends Cell {

    private RoomEvent roomEvent;

    public RoomCell(RoomEvent roomEvent) {
        this.roomEvent = roomEvent;
    }

    @Override
    public void interact(Character hero) {

        if (roomEvent != null) {
            roomEvent.trigger(hero);
        } else {
            System.out.println("La salle est vide...");
        }
    }

    @Override
    public String toString() {
        return "Salle spéciale";
    }
}