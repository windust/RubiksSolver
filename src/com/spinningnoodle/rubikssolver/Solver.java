package com.spinningnoodle.rubikssolver;

import com.spinningnoodle.rubikssolver.library.Cube;
import com.spinningnoodle.rubikssolver.library.Face;

import java.util.*;

/**
 * Created by Freddy on 3/29/2014.
 * Actual Solver class
 */
public class Solver {
    Queue<CubeWithTransforms> queue = new LinkedList<>();

    public static List<Move> solve (Cube cube) {
        Solver solver = new Solver();
        return solver.doSolve (cube, null);
    }

    private List<Move> doSolve(Cube rootCube, Move priorMove) {
        queue.clear();
        queue.add(new CubeWithTransforms(rootCube, Collections.emptyList()));
        while (!queue.isEmpty()) {
            CubeWithTransforms transforms = queue.remove();
            Cube cube = transforms.getCube();
//            int score = cube.getScore();
            Move cancelingMove = priorMove == null ? null : new Move(priorMove.type, !priorMove.clockwise, cube, null);
            //List<Cube> cubesToTry = new ArrayList<>();
            boolean addAll = true;
            for (Face.FaceType type : Face.FaceType.values()) {
                for (int i =0;i < 2;i++) {
                    boolean clockWise = i == 0;
                    Move newMove = new Move(type, clockWise, cube, null);
                    if (newMove.equals(cancelingMove)) continue;
                    if (looped(newMove, transforms.getMoves())) continue;
                    Cube newCube = new Cube(cube);
                    newCube.rotate(newMove.type, newMove.clockwise);
                    List<Move> newMoves = new ArrayList<>(transforms.getMoves());
                    newMoves.add(newMove);

                    if (newCube.isSolved()) {
                        // We solved it!.
                        System.out.println("Solved!");
                        printSolution(transforms.getMoves());
                        return newMoves;
                    }
                    // check end game

                    queue.add(new CubeWithTransforms(newCube, newMoves));
                    if (queue.size() % 1000 == 0) System.out.println(queue.size());
                }
            }

//            if (addAll) {
//                queue.addAll(cubesToTry);
//                System.out.println("Adding "+queue.size());
//            } else {
//                for (Cube aCube : cubesToTry) {
//                    if (aCube.getScore() > score) {
//                        queue.add(aCube);
//                        if (queue.size() % 1000 == 0) {
//                            System.out.println(queue.size());
//                        }
//                    }
//                }
//            }



        }




//        for (Move.FaceType faceType : Move.FaceType.values()) {
//            Cube newCube = new Cube(cube);
//        }


        return null;
    }

    private boolean looped(Move newMove, List<Move> moves) {
        if (moves.size() > 2) {
            for (int i = moves.size()-1;i >= moves.size()-3;i--) {
                if (!newMove.equals(moves.get(i))) return false;
            }
            return true;
        }
        return false;

    }

    private void printSolution(List<Move> moves) {
        for (Move move : moves) {
            move.print();
        }

    }


    public static class Move {
        final Face.FaceType type;
        final boolean clockwise;
        final Cube cube;
        final List<Move> priorMoves;

        public Move(Face.FaceType type, boolean clockwise, Cube cube, List<Move> priorMoves) {
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

        public void print() {
            System.out.println("Face :"+type+" Rotate :"+(clockwise ? "Clockwise" : "Counter"));
        }
    }

    public static class CubeWithTransforms {
        Cube cube;
        List<Move> moves;

        public CubeWithTransforms(Cube cube, List<Move> moves) {
            this.cube = cube;
            this.moves = moves;
        }

        public Cube getCube() {
            return cube;
        }

        public List<Move> getMoves() {
            return moves;
        }
    }

}
