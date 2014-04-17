package com.spinningnoodle.rubikssolver.library;

/**
 * Created by Freddy on 4/16/2014.
 * Edge represents edge pieces that have two colors.
 */
public class Edge {
    Face.FColor topColor;
    Face.FColor sideColor;

    public Edge(Face.FColor topColor, Face.FColor sideColor) {
        this.topColor = topColor;
        this.sideColor = sideColor;
    }

    public boolean found(Face.FColor faceColor, Face.FColor anotherFaceColor) {
        if (faceColor == topColor && anotherFaceColor == sideColor) return true;
        if (anotherFaceColor == topColor && faceColor == sideColor) return true;
        return false;

    }
}
