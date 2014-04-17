package com.spinningnoodle.rubikssolver.library.solver.algo;

import com.spinningnoodle.rubikssolver.library.Cube;
import com.spinningnoodle.rubikssolver.library.Edge;
import com.spinningnoodle.rubikssolver.library.FaceType;
import com.spinningnoodle.rubikssolver.library.solver.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Freddy on 4/16/2014.
 */
public class EdgeState extends State {
    @Override
    public State transform(Cube cube, List<Move> moves) {
        // try the four faces, see which one uses less moves.
        List<Move> faceMoves = null;
        FaceType selectedType = null;
        for (FaceType faceType : FaceType.values()) {
            List<Move> currentMoves = solveEdge(cube, faceType);
            if (currentMoves != null && (faceMoves == null || faceMoves.size() > currentMoves.size())) {
                faceMoves = currentMoves;
                selectedType = faceType;
            }
        }
        if (faceMoves == null) return null;
        moves.addAll(faceMoves);
        return new CornerState(selectedType);

    }

    private List<Move> solveEdge(Cube cube, FaceType faceType) {
        List<Move> moves = new ArrayList<>();
        for (FaceType neighbor : FaceType.getNeighbors(faceType)) {
            solveEdge(cube, faceType, neighbor, moves);
        }
        return moves;
    }

    private void solveEdge(Cube cube, FaceType faceType, FaceType neighbor, List<Move> moves) {
        Edge edge = new Edge(cube.getFaceColor(faceType), cube.getFaceColor(neighbor));
        for (FaceType type : FaceType.values()) {
            if (type == faceType) continue; // not in the "same" face we are looking at...
            if (findEdge(cube, type, edge)) {
                // we found it.

            }
        }

    }

    private boolean findEdge(Cube cube, FaceType type, Edge edge) {
        if (edge.found(cube.getFace(type).getColor(0,1), cube.getFace(FaceType.getNorth(type)).getColor(3,1))) {
            return true;
        }

//        if (edge.found(cube.getFace(type).getColor(1,2), cube.getFace(FaceType.getEast(type)).getColor(3,1))) {
//            return true;
//        }


        FaceType.getNorth(type);
        return false;
    }

    public static class FaceWithPosition;



}
