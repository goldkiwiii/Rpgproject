import javax.swing.*;

public class Frame extends JFrame {

    private static Character mainCharacter = new Character();
    public Frame(){
        setTitle("poketmon");
        setSize(700, 700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getCharacter().makeLocation();

        setVisible(true);

    }

    public static Character getCharacter(){
        return mainCharacter;
    }
}
