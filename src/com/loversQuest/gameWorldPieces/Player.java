package com.loversQuest.gameWorldPieces;

public class Player {

    private String name;
    private Location currentLocation;

    //  making item instance for testing
//    Item item = new Item("WhiteClaw", 1);
    private RuckSack ruckSack = new RuckSack();

//    RuckSack ruckSack = new RuckSack(item.getName(), item.getQuantity());

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

        switch (direction) {
            case "east":
                if (validateLocation(this.currentLocation.getEast())) {
                    this.setCurrentLocation(this.currentLocation.getEast());
                } else {
                    System.out.println("You can't go that way");
                }
                break;
            case "west":
                if (validateLocation(this.currentLocation.getWest())) {
                    this.setCurrentLocation(this.currentLocation.getWest());
                } else {
                    System.out.println("You can't go that way");
                }
                break;
            case "south":
                if (validateLocation(this.currentLocation.getSouth())) {
                    this.setCurrentLocation(this.currentLocation.getSouth());
                } else {
                    System.out.println("You can't go that way");
                }
                break;
            case "north":
                if (validateLocation(this.currentLocation.getNorth())) {
                    this.setCurrentLocation(this.currentLocation.getNorth());
                } else {
                    System.out.println("You can't go that way");
                }
                break;
            default:
                System.out.println("bad input, try again");
                return false;
        }
        return true;
    }

    // checks if a given location is a place a player can move
    public boolean validateLocation(Location destination){
        return !destination.getName().equals("NOTHING");
    }

    public String look() {
        return ("You look around and " + this.getCurrentLocation().getDescription());
    }

    public String interact(){
        if(currentLocation.getOccupant() == null){
            return "There is no one here";
        }else{
            return currentLocation.getOccupant().getName() + " is here.";
        }

    }

    public void addItem(Item item) {
        // call item.addItem() to add item/quantity to ruckSack
        ruckSack.addItem(item);
    }

    public void getItem(String itemName){
        //loop through items in current location
        // if string itemName matches an item in current location
            // add item to inventory and remove item from location
    }

    public String displayItems() {
        return ruckSack.displayRuckSackContents();
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
}
