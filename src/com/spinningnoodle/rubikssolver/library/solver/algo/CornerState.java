package com.spinningnoodle.rubikssolver.library.solver.algo;

import com.spinningnoodle.rubikssolver.library.Cube;
import com.spinningnoodle.rubikssolver.library.FaceType;
import com.spinningnoodle.rubikssolver.library.solver.Move;

import java.util.List;

/**
 * Created by Freddy on 4/16/2014.
 */
public class CornerState extends State{

    private final FaceType type;

    public CornerState(FaceType type) {
        this.type = type;
    }

    @Override
    public State transform(Cube cube, List<Move> moves) {
        return null;
    }
}
