package map;

public interface Map {

    void setPlayerOnMap();
    void setMonsterOnMap();
    void setChestOnMap();
    void move();
    void showMap();
    boolean isNearMonster();
    boolean isNearChest();
    boolean isNearExit();

    void setMapCordToFloor();


}
