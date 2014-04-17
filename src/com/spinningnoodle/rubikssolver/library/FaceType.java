package com.spinningnoodle.rubikssolver.library;

/**
* Created by Freddy on 4/16/2014.
*/
public enum FaceType {

    North,South,East,West,Near,Far;


    public static FaceType[] getNeighbors(FaceType top) {
        switch (top) {
            case North:
                return new FaceType[] {West, Near, East, Far};
            case South:
                return new FaceType[] {East, Near,  West, Far};
            case East:
                return new FaceType[] {North, Far, South, Near};
            case West:
                return new FaceType[] {North, Near, South, Far};
            case Near:
                return new FaceType[] {North, East, South, West};
            case Far:
                return new FaceType[] {North, West, South, Near};

        }
        return null;
    }

    public static FaceType getNorth(FaceType original) {
        switch (original) {
            case Near:
                return North;
            case Far:
                return South;
            case East:
                return North;
            case West:
                return South;
            case North:
                return Far;
            case South:
                return Near;
        }
        return null;
    }

    public static FaceType getSouth(FaceType original) {

        switch (original) {
            case Near:
                return South;
            case Far:
                return North;
            case East:
                return South;
            case West:
                return North;
            case North:
                return Near;
            case South:
                return Far;
        }
        return null;
    }

    public static FaceType getEast(FaceType original) {
        switch (original) {
            case Near:
                return East;
            case Far:
                return West;
            case East:
                return Far;
            case West:
                return Near;
            case North:
                return East;
            case South:
                return West;
        }
        return null;
    }

    public static FaceType getWest(FaceType original) {
        switch (original) {
            case Near:
                return West;
            case Far:
                return East;
            case East:
                return Near;
            case West:
                return Far;
            case North:
                return West;
            case South:
                return East;
        }
        return null;
    }

}
