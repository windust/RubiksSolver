package com.spinningnoodle.rubikssolver.library.solver.algo;

import com.spinningnoodle.rubikssolver.library.Cube;
import com.spinningnoodle.rubikssolver.library.solver.Move;
import com.spinningnoodle.rubikssolver.library.solver.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Freddy on 4/16/2014.
 */
public class AlgoSolver implements Solver {
    @Override
    public List<Move> solve(Cube cube) {
        List<Move> moves = new ArrayList<>();
        State state = new EdgeState();
        while (!cube.isSolved()) {
            state = state.transform(cube, moves);
            if (state == null) {
                System.out.println("Cannot solve!");
                return Collections.emptyList();
            }
        }
        return null;
    }



}
