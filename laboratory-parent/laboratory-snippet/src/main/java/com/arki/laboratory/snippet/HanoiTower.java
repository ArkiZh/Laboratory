package com.arki.laboratory.snippet;

import com.arki.laboratory.common.Logger;

public class HanoiTower {

    public static void main(String[] args) {
        moveTower(3, "A", "B", "C");
    }

    public static void moveTower(int size, String fromTower, String toTower, String spaceTower) {
        if(size==1) Logger.info("Move: {} --> {}", fromTower, toTower);
        else{
            moveTower(size - 1, fromTower, spaceTower, toTower);
            moveTower(1, fromTower, toTower, spaceTower);
            moveTower(size - 1, spaceTower, toTower, fromTower);
        }
    }
}
