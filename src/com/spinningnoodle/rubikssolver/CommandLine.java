package com.spinningnoodle.rubikssolver;

import com.spinningnoodle.rubikssolver.library.Cube;
import com.spinningnoodle.rubikssolver.library.FaceType;
import com.spinningnoodle.rubikssolver.library.solver.Move;
import com.spinningnoodle.rubikssolver.library.solver.SolverFactory;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Freddy on 3/29/2014.
 */
public class CommandLine {
    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine();
        commandLine.start();
    }

    private void start() {
        System.out.println("Rubiks solver");
        Scanner scanner = new Scanner(System.in);
        Cube cube = new Cube();
        boolean running = true;
        while (running) {
            String command = scanner.nextLine();
            command = command.toUpperCase();
            switch (command) {
                case "Q":
                    running = false;
                    break;
                case "R":
                    cube.rotate(FaceType.Near, true);
                    cube.print(FaceType.Near);
                    break;
                case "E":
                    cube.rotate(FaceType.Near, false);
                    cube.print(FaceType.Near);
                    break;
                case "8":
                    cube.print(FaceType.North);
                    break;
                case "2":
                    cube.print(FaceType.South);
                    break;
                case "6":
                    cube.print(FaceType.East);
                    break;
                case "4":
                    cube.print(FaceType.West);
                    break;
                case "7":
                    cube.print(FaceType.Near);
                    break;
                case "9":
                    cube.print(FaceType.Far);
                    break;
                case "S":
                    cube.scramble();
                    cube.printAll();
                    List<Move> moves = SolverFactory.getSolver(SolverFactory.Type.TREE).solve(cube);
                    // do them
                    for (Move move : moves ) {
                        cube.rotate(move.getType(), move.isClockwise());
                    }

                    cube.printAll();
                    break;



            }
            if (command.equals("Q")) break;


        }
        scanner.close();

    }

}
