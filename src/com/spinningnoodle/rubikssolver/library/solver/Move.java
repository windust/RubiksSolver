package com.spinningnoodle.rubikssolver.library.solver;

import com.spinningnoodle.rubikssolver.library.Cube;
import com.spinningnoodle.rubikssolver.library.FaceType;

import java.util.List;

/**
* Created by Freddy on 4/16/2014.
*/
public class Move {
    final FaceType type;
    final boolean clockwise;
    final Cube cube;
    final List<Move> priorMoves;

    public Move(FaceType type, boolean clockwise, Cube cube, List<Move> priorMoves) {
        this.type = type;
        this.clockwise = clockwise;
        this.cube = cube;
        this.priorMoves = priorMoves;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (clockwise != move.clockwise) return false;
        if (type != move.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + (clockwise ? 1 : 0);
        return result;
    }

    public FaceType getType() {
        return type;
    }

    public boolean isClockwise() {
        return clockwise;
    }

    public void print() {
        System.out.println("Face :"+type+" Rotate :"+(clockwise ? "Clockwise" : "Counter"));
    }
}
