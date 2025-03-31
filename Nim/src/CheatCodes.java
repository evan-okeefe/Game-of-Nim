import java.util.ArrayList;

public class CheatCodes {

    private StringBuilder buffer = new StringBuilder();

    private ArrayList<String> cheatCodes = new ArrayList<>();
    private ArrayList<Runnable> cheats = new ArrayList<>();

    public void addCheatCode(String code, Runnable cheat){
        cheatCodes.add(code);
        cheats.add(cheat);
    }

    public void checkCheat(char key){
        System.out.println(key);

        buffer.append(key);

        if (buffer.length() > 15) {
            buffer.deleteCharAt(0);
        }

        String input = buffer.toString();
        for (int i = 0; i < cheatCodes.size(); i++) {
            if (input.endsWith(cheatCodes.get(i))) {
                cheats.get(i).run();
                buffer.setLength(0);
                break;
            }
        }
    }
}
