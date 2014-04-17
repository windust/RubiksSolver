package com.spinningnoodle.rubikssolver.library.solver.algo;

import com.spinningnoodle.rubikssolver.library.Cube;
import com.spinningnoodle.rubikssolver.library.solver.Move;

import java.util.List;

/**
 * Created by Freddy on 4/16/2014.
 */

public abstract class State {
    public abstract State transform(Cube cube, List<Move> moves);
}
