package monster;

@SuppressWarnings("unused")
public class NullPointerException extends Monster{
    public NullPointerException(double diff) {
        this.setHp((int) (20*diff));
        this.setMaxHP((int) (20*diff));
        this.setDmg((int) (5*diff));
        this.setMana(0);
        this.setDefense((int) (2*diff));
    }
}
