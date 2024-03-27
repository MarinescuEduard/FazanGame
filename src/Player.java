public class Player {
    private String name;
    private String life = "";

    public Player(String name) {
        this.name = name; // Player Name
    }

    public String getName() {
        return name; // Return player name
    }

    public String getLife() {
        return life; // Return player life status
    }

    public void addLetterToLife() {
        // Update the life of a player
        switch (life.length()) {
            case 0:
                life += "F";
                break;
            case 1, 3:
                life += "a";
                break;
            case 2:
                life += "z";
                break;
            case 4:
                life += "n";
                break;
        }
        // Show the life of the player
        System.out.println(name + " now has " + life);
    }

    public boolean hasLost() {
        return life.equals("Fazan"); // Check if player has lost
    }
}
