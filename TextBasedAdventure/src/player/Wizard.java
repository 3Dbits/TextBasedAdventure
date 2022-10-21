package player;

@SuppressWarnings("unused")
public class Wizard extends Player {
    public Wizard() {
        this.setRole("Wizard");
        this.setHp(60);
        this.setMaxHP(60);
        this.setDmg(8);
        this.setMana(100);
        this.setMaxMana(100);
        this.setDefense(1);
    }

}
