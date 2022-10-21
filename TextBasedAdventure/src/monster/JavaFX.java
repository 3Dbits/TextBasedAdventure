package monster;

@SuppressWarnings("unused")
public class JavaFX extends Monster {
    public JavaFX(double diff) {
        this.setHp((int) (100*diff));
        this.setMaxHP((int) (100*diff));
        this.setDmg((int) (10*diff));
        this.setMana(50);
        this.setMaxMana(50);
        this.setDefense((int) (5*diff));
    }
}
