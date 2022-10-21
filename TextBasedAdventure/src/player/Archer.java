package player;

@SuppressWarnings("unused")
public class Archer extends Player{
    public Archer() {
        this.setRole("Archer");
        this.setHp(70);
        this.setMaxHP(70);
        this.setDmg(12);
        this.setMana(40);
        this.setMaxMana(40);
        this.setDefense(2);
    }

}
