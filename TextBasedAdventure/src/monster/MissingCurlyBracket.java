package monster;

@SuppressWarnings("unused")
public class MissingCurlyBracket extends Monster {
    public MissingCurlyBracket(double diff) {
        this.setHp((int) (25*diff));
        this.setMaxHP((int) (25*diff));
        this.setDmg((int) (6*diff));
        this.setMana(0);
        this.setDefense((int) (2*diff));
    }
}