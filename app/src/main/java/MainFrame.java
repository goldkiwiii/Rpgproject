import javax.swing.*;

public class MainFrame extends JFrame {

    private static Character mainCharacter = new Character();
    public MainFrame(){
        setTitle("poketmon");
        setSize(700, 700);

        getContentPane().add(getCharacter().makeLocation());


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);

    }

    public static Character getCharacter(){
        return mainCharacter;
    }
}
