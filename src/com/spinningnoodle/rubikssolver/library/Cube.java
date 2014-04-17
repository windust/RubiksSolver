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
    static Map<FaceType, List<Link>> faceLinkMap = new HashMap<>();

    static {
        // near face links
        addLink(FaceType.Near, FaceType.North, 2, true);
        addLink(FaceType.Near, FaceType.East, 2, true);
        addLink(FaceType.Near, FaceType.South, 2, true);
        addLink(FaceType.Near, FaceType.West, 2, true);


        // Face.FaceType.Far face links
        addLink(FaceType.Far, FaceType.North, 0, true);
        addLink(FaceType.Far, FaceType.West, 0, true);
        addLink(FaceType.Far, FaceType.South, 0, true);
        addLink(FaceType.Far, FaceType.East, 0, true);

        // Face.FaceType.East
        addLink(FaceType.East, FaceType.Near, 0, false);
        addLink(FaceType.East, FaceType.North, 0, false);
        addLink(FaceType.East, FaceType.Far, 0, false);
        addLink(FaceType.East, FaceType.South, 0, false);

        // Face.FaceType.West
        addLink(FaceType.West, FaceType.Near, 2, false);
        addLink(FaceType.West, FaceType.South, 2, false);
        addLink(FaceType.West, FaceType.Far, 2, false);
        addLink(FaceType.West, FaceType.North, 2, false);

        // Face.FaceType.North
        addLink(FaceType.North, FaceType.Near, 0, true);
        addLink(FaceType.North, FaceType.West, 0, true);
        addLink(FaceType.North, FaceType.Far, 0, true);
        addLink(FaceType.North, FaceType.East, 0, true);

        // Face.FaceType.South
        addLink(FaceType.South, FaceType.Near, 2, true);
        addLink(FaceType.South, FaceType.East, 2, true);
        addLink(FaceType.South, FaceType.Far, 2, true);
        addLink(FaceType.South, FaceType.West, 2, true);
    }

    Map<FaceType, Face > faceTypeMap = new HashMap<>();
    public Cube() {
        add(new Face(Face.FColor.Red, FaceType.North));
        add(new Face(Face.FColor.Blue, FaceType.South));
        add(new Face(Face.FColor.Green, FaceType.East));
        add(new Face(Face.FColor.Orange, FaceType.West));
        add(new Face(Face.FColor.White, FaceType.Near));
        add(new Face(Face.FColor.Yellow, FaceType.Far));
    }


    public Cube(Cube cube) {
        for (Map.Entry<FaceType, Face> entry : cube.faceTypeMap.entrySet()) {
            add(new Face(entry.getValue()));
        }
    }

    private void add(Face face) {
        faceTypeMap.put(face.getType(), face);
    }

    private static void addLink(FaceType originalFace, FaceType linkedFace, int line, boolean horizontal) {
        List<Link> links = faceLinkMap.get(originalFace);
        if (links == null) {
            links = new ArrayList<>();
            faceLinkMap.put(originalFace, links);
        }
        links.add(new Link(linkedFace, line, horizontal));
    }

    public void rotate(FaceType type , boolean clockWise) {
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
            FaceType type = FaceType.values()[random.nextInt(FaceType.values().length)];
            rotate(type, random.nextBoolean());
        }
    }

    public boolean isSolved() {
        for (Face face : faceTypeMap.values()) {
            if (!face.isSolved()) return false;
        }
        return true;
    }

    public void print(FaceType type) {
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
        print(FaceType.North);
        print(FaceType.South);
        print(FaceType.East);
        print(FaceType.West);
        print(FaceType.Near);
        print(FaceType.Far);

    }

    public Face.FColor getFaceColor (FaceType type) {
        return faceTypeMap.get(type).getFaceColor();
    }

    public Face getFace(FaceType type) {
        return faceTypeMap.get(type);

    }


    private static class Link {
        FaceType type;
        int line;
        boolean horizontal;

        private Link(FaceType type, int line, boolean horizontal) {
            this.type = type;
            this.line = line;
            this.horizontal = horizontal;
        }

        public FaceType getType() {
            return type;
        }
    }

}
