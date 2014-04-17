package com.spinningnoodle.rubikssolver;

import com.spinningnoodle.rubikssolver.library.Cube;
import com.spinningnoodle.rubikssolver.library.Face;

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
                    cube.rotate(Face.FaceType.Near, true);
                    cube.print(Face.FaceType.Near);
                    break;
                case "E":
                    cube.rotate(Face.FaceType.Near, false);
                    cube.print(Face.FaceType.Near);
                    break;
                case "8":
                    cube.print(Face.FaceType.North);
                    break;
                case "2":
                    cube.print(Face.FaceType.South);
                    break;
                case "6":
                    cube.print(Face.FaceType.East);
                    break;
                case "4":
                    cube.print(Face.FaceType.West);
                    break;
                case "7":
                    cube.print(Face.FaceType.Near);
                    break;
                case "9":
                    cube.print(Face.FaceType.Far);
                    break;
                case "S":
                    cube.scramble();
                    cube.printAll();
                    List<Solver.Move> moves = Solver.solve(cube);
                    // do them
                    for (Solver.Move move : moves ) {
                        cube.rotate(move.type,move.clockwise);
                    }
                    cube.printAll();
                    break;



            }
            if (command.equals("Q")) break;


        }
        scanner.close();

    }

}
