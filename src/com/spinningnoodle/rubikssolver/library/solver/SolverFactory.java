package com.spinningnoodle.rubikssolver.library.solver;

import com.spinningnoodle.rubikssolver.library.solver.algo.AlgoSolver;

/**
 * Created by Freddy on 4/16/2014.
 */
public class SolverFactory {
    public static enum Type {TREE, ALGO}
    public static Solver getSolver (Type type) {
        switch (type) {
            case TREE:
                return new TreeSolver();
            case ALGO:
            default:
                return new AlgoSolver();
        }
    }
}
