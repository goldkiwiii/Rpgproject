import javax.swing.*;
import java.awt.*;

public class Character {
    private String name;
    private int hp;
    private int strength;
    private int intelligence;
    private int money;
    private Item[] items;
    private int level;
    private int exp;
    private String img;

    static JPanel characterPanel = new JPanel();

    static int[][] location = new int[7][7];

    public JPanel makeLocation() {
        characterPanel.setBounds(MainFrame.WIDTH / 7, MainFrame.HEIGHT / 7,
                5 * MainFrame.HEIGHT / 7, 5 * MainFrame.HEIGHT / 7);
        characterPanel.setBackground(new Color(100, 10, 55));
        characterPanel.setVisible(true);
        return characterPanel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }



}
