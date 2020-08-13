package packing.impl;

import packing.Box;

public final class PermutationRotation {

    private final int count;
    private final Box[] boxes;

    PermutationRotation(int count, Box[] boxes) {
        this.count = count;
        this.boxes = boxes;
    }

    Box[] getBoxes() {
        return boxes;
    }

    public int getCount() {
        return count;
    }
}
