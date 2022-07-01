package tennisClub.diagram;

/**
 * @author Matus Jakab
 */

/*
    represents type of surface of a court
    each surface has an own price per minute
 */

public enum Surface {
    GRASS(0.16){
        public String toString(){
            return "Grass";
        }
    },
    CLAY(0.12){
        public String toString() {
        return "Clay";
        }
    },
    ARTIFICIAL(0.14){
        public String toString() {
            return "Artificial";
        }
    },
    HARD(0.18){
        public String toString() {
            return "Hard";
        }
    };

    private final double price;
    Surface(double price){
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
