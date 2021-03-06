package com.loversQuest.gameWorldPieces;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.loversQuest.IO.GraphicClass;

import java.io.IOException;

public class Player {

    private String name;
    private Location currentLocation;

    private boolean hasChallengeCoin = false;
    private boolean hasKiss = false;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";


    public RuckSack ruckSack = new RuckSack();


    // CTOR
    public Player(String name, Location currentLocation) {
        this.name = name;
        this.currentLocation = currentLocation;
    }

    // BUSINESS METHODS

    //go function can result in navigating to "NOTHING" area. need to error check if
    // indicated direction is not a room and prevent movement.
    public boolean go(String directionInput) {

        String direction = directionInput.toLowerCase();
        String response = null;

        boolean result = false;

        // conditions for officer functionality. (Preventing us from going to the west)
        if (getCurrentLocation().getOccupant() instanceof Officer) {
            if (direction.equals("west")) {
                if (getItem(getCurrentLocation().getOccupant().getPrize().getName()) == null) {
                    ((Officer) getCurrentLocation().getOccupant()).reRoute(this);
                    return false;
                }
            }
        }

        //get indicated destination from direction string
        Location destination = this.currentLocation.getDirectionFromString(directionInput);
        // if it is a valid direction to go, update current position

        if (destination != null && validateLocation(destination)) {
            this.setCurrentLocation(destination);
            result = true;
        }
        return result;

    }

    // checks if a given location is a place a player can move
    public boolean validateLocation(Location destination) {
        return !destination.getName().equals("NOTHING");
    }

    public String look() {
            return ("You look around and " + this.getCurrentLocation().getDescription());
    }

    public String interact() {
        if (currentLocation.getOccupant() == null) {
            return "There is no one here";
        } else {
            return currentLocation.getOccupant().getName() + " is here.\nThey say: " + currentLocation.getOccupant().interact(this);
        }
    }

    public void addItem(Item item) {
        // call item.addItem() to add item/quantity to ruckSack
        ruckSack.addItem(item);
    }

    public Item getItem(String itemName) {
        return this.ruckSack.getItem(itemName.toLowerCase());
    }

    public boolean pickUpItem(String itemName) {
        //loop through items in current location
        boolean gotItem = false;
        for (int i = 0; i < currentLocation.getItemsList().size(); i++) {
            Item locationItem = currentLocation.getItemsList().get(i);
            // first portion originally: itemName.toLowerCase().equals(locationItem.getName().toLowerCase()
            if (locationItem.getName().toLowerCase().contains(itemName.toLowerCase()) && !(locationItem instanceof Container)) {
                this.addItem(locationItem);
                currentLocation.removeItem(locationItem);
                gotItem = true;
            }
        }
        return gotItem;
        // if string itemName matches an item in current location
        // add item to inventory and remove item from location
    }

    public ArrayList<Item> inspect() {
        ArrayList<Item> result = null;
        if(currentLocation.getContainer() != null){
            result = currentLocation.getContainer().displayContents();
        }
        return result;
    }

    public String displayItems() {
        return ruckSack.displayRuckSackContents();
    }

    public List<Item> getAllItems() {
        return ruckSack.items;
    }

    // SETTERS/GETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

//    public void printCurrentAscii() throws IOException {
//        //            this.currentLocation.getName().toLowerCase().equals("gazebo");
//
////        String printLocation = this.currentLocation.getName().toLowerCase();
//
//        switch (this.currentLocation.getName().toLowerCase()) {
//            case BLUE + "laundryroom" + ANSI_RESET -> graphicImage.printLocation("laundryRoom.txt");
//            case BLUE + "barracks" + ANSI_RESET -> graphicImage.printLocation("home.txt");
//            case BLUE + "gym" + ANSI_RESET -> graphicImage.printLocation("gym.txt");
//            case BLUE + "courtyard" + ANSI_RESET -> graphicImage.printLocation("courtYard.txt");
//            case BLUE + "range" + ANSI_RESET -> graphicImage.printLocation("range.txt");
//            case BLUE + "portajohn" + ANSI_RESET -> graphicImage.printLocation("portaJohn.txt");
//            case BLUE + "chowhall" + ANSI_RESET -> graphicImage.printLocation("chowHall.txt");
//            case BLUE + "px" + ANSI_RESET -> graphicImage.printLocation("px.txt");
//            default -> graphicImage.printLocation("gazebo.txt");
//        }

    public boolean isHasChallengeCoin() {
        return hasChallengeCoin;
    }

    public void setHasChallengeCoin(boolean hasChallengeCoin) {
        this.hasChallengeCoin = hasChallengeCoin;
    }

    public boolean isHasKiss() {
        return hasKiss;
    }

    public void setHasKiss(boolean hasKiss) {
        this.hasKiss = hasKiss;
    }
}

