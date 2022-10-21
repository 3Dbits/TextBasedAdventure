package monster;

@SuppressWarnings("unused")
public class MergeConflict extends Monster {
    public MergeConflict(double diff) {
        this.setHp((int) (30*diff));
        this.setMaxHP((int) (30*diff));
        this.setDmg((int) (8*diff));
        this.setMana(0);
        this.setDefense((int) (2*diff));
    }
}