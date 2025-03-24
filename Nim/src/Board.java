public class Board {
    private static int numTiles;

    public static void populate(){
        numTiles = (int)(Math.random() * 40) + 10;
    }

    public static void takeTiles(int num){
        numTiles -= num;
    }

    public static void display(){
        System.out.println(numTiles + ":");
        for (int i = 0; i < numTiles; i++) {
            System.out.print("($)");
        }
        System.out.println();
    }

    public static int getNumTiles(){
        return numTiles;
    }
}
