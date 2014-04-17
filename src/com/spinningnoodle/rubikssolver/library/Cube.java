package com.spinningnoodle.rubikssolver.library;

import java.util.*;

/**
 * Created by Freddy on 3/27/2014.
 *
 */

public class Cube {


//    final Face north;
//    final Face south;
//    final Face east;
//    final Face west;
//    final Face near;
//    final Face far;
    static Map<Face.FaceType, List<Link>> faceLinkMap = new HashMap<>();

    static {
        // near face links
        addLink(Face.FaceType.Near, Face.FaceType.North, 2, true);
        addLink(Face.FaceType.Near, Face.FaceType.East, 2, true);
        addLink(Face.FaceType.Near, Face.FaceType.South, 2, true);
        addLink(Face.FaceType.Near, Face.FaceType.West, 2, true);


        // Face.FaceType.Far face links
        addLink(Face.FaceType.Far, Face.FaceType.North, 0, true);
        addLink(Face.FaceType.Far, Face.FaceType.West, 0, true);
        addLink(Face.FaceType.Far, Face.FaceType.South, 0, true);
        addLink(Face.FaceType.Far, Face.FaceType.East, 0, true);

        // Face.FaceType.East
        addLink(Face.FaceType.East, Face.FaceType.Near, 0, false);
        addLink(Face.FaceType.East, Face.FaceType.North, 0, false);
        addLink(Face.FaceType.East, Face.FaceType.Far, 0, false);
        addLink(Face.FaceType.East, Face.FaceType.South, 0, false);

        // Face.FaceType.West
        addLink(Face.FaceType.West, Face.FaceType.Near, 2, false);
        addLink(Face.FaceType.West, Face.FaceType.South, 2, false);
        addLink(Face.FaceType.West, Face.FaceType.Far, 2, false);
        addLink(Face.FaceType.West, Face.FaceType.North, 2, false);

        // Face.FaceType.North
        addLink(Face.FaceType.North, Face.FaceType.Near, 0, true);
        addLink(Face.FaceType.North, Face.FaceType.West, 0, true);
        addLink(Face.FaceType.North, Face.FaceType.Far, 0, true);
        addLink(Face.FaceType.North, Face.FaceType.East, 0, true);

        // Face.FaceType.South
        addLink(Face.FaceType.South, Face.FaceType.Near, 2, true);
        addLink(Face.FaceType.South, Face.FaceType.East, 2, true);
        addLink(Face.FaceType.South, Face.FaceType.Far, 2, true);
        addLink(Face.FaceType.South, Face.FaceType.West, 2, true);
    }

    Map<Face.FaceType, Face > faceTypeMap = new HashMap<>();
    public Cube() {
        add(new Face(Face.FColor.Red, Face.FaceType.North));
        add(new Face(Face.FColor.Blue, Face.FaceType.South));
        add(new Face(Face.FColor.Green, Face.FaceType.East));
        add(new Face(Face.FColor.Orange, Face.FaceType.West));
        add(new Face(Face.FColor.White, Face.FaceType.Near));
        add(new Face(Face.FColor.Yellow, Face.FaceType.Far));
    }


    public Cube(Cube cube) {
        for (Map.Entry<Face.FaceType, Face> entry : cube.faceTypeMap.entrySet()) {
            add(new Face(entry.getValue()));
        }
    }

    private void add(Face face) {
        faceTypeMap.put(face.getType(), face);
    }

    private static void addLink(Face.FaceType originalFace, Face.FaceType linkedFace, int line, boolean horizontal) {
        List<Link> links = faceLinkMap.get(originalFace);
        if (links == null) {
            links = new ArrayList<>();
            faceLinkMap.put(originalFace, links);
        }
        links.add(new Link(linkedFace, line, horizontal));
    }

    public void rotate(Face.FaceType type , boolean clockWise) {
//        System.out.println("Rotating :"+type+" "+clockWise);
        Face rotatingFace = faceTypeMap.get(type);
        rotatingFace.transpose(clockWise);
        List<Link> links = faceLinkMap.get(type);

        Link lastLink = links.get(links.size() - 1);
        Link firstLink = links.get(0);
        if (clockWise) {
            Face.FColor[] lastColors = getColors(lastLink);
            for (int i= links.size()-2;i >= 0;i--) {
                setColors(links, i+1, i);
            }
            setColors(firstLink, lastColors);
        } else {
            Face.FColor[] firstColors = getColors(firstLink);
            for (int i= 0;i < links.size()-1;i++) {
                setColors(links, i, i+1);
            }
            setColors(lastLink, firstColors);
        }
    }

    private Face.FColor[] getColors(Link link) {
        return faceTypeMap.get(link.getType()).getColors(link.line, link.horizontal);
    }

    private void setColors(Link link, Face.FColor[] sourceColors) {
        Face destFace = faceTypeMap.get(link.getType());
        destFace.setColors(link.line, link.horizontal, sourceColors);
    }

    private void setColors(List<Link> links, int dest, int source) {
        Link destLink = links.get(dest);
        Link sourceLink = links.get(source);
        Face sourceFace = faceTypeMap.get(sourceLink.getType());
        setColors(destLink, sourceFace.getColors(sourceLink.line, sourceLink.horizontal));
    }



    Random random = new Random();

    public void scramble() {
        // let's scramble.
        for (int i =0;i < 3;i++) {
            Face.FaceType type = Face.FaceType.values()[random.nextInt(Face.FaceType.values().length)];
            rotate(type, random.nextBoolean());
        }
    }

    public boolean isSolved() {
        for (Face face : faceTypeMap.values()) {
            if (!face.isSolved()) return false;
        }
        return true;
    }

    public void print(Face.FaceType type) {
        System.out.println(type);
        faceTypeMap.get(type).print();
        System.out.println();
    }

    public int getScore() {
        int score = 0;
        for (Face face : faceTypeMap.values()) {
            score += face.getScore();
        }
        return score;
    }

    public void printAll() {
        print(Face.FaceType.North);
        print(Face.FaceType.South);
        print(Face.FaceType.East);
        print(Face.FaceType.West);
        print(Face.FaceType.Near);
        print(Face.FaceType.Far);

    }


    private static class Link {
        Face.FaceType type;
        int line;
        boolean horizontal;

        private Link(Face.FaceType type, int line, boolean horizontal) {
            this.type = type;
            this.line = line;
            this.horizontal = horizontal;
        }

        public Face.FaceType getType() {
            return type;
        }
    }

}
