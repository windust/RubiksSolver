package com.spinningnoodle.rubikssolver.library.solver;

import com.spinningnoodle.rubikssolver.library.Cube;

import java.util.List;

/**
 * Created by Freddy on 4/16/2014.
 */
public interface Solver {
    List<Move> solve(Cube rootCube);
}
