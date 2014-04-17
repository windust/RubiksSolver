package com.spinningnoodle.rubikssolver.library;

/**
 * Created by Freddy on 3/27/2014.
 * face
 */
public class Face {
    public enum FColor {White, Red, Blue, Orange, Green, Yellow}
    public enum FaceType {North,South,East,West,Near,Far}
    private final FaceType type;

    private FColor[][] colors = new FColor[3][3];

    public Face(Face that) {
        this.type = that.type;
        for (int i = 0; i < 3; i++) {
            System.arraycopy(that.colors[i], 0, colors[i], 0, 3);
        }
    }


    public Face(FColor color, FaceType type) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                colors[i][j] = color;
            }
        }
        this.type = type;
    }

    public void setColor(int x, int y, FColor color) {
        colors[x][y] = color;
    }

    public void setColors(int line, boolean horizontal, FColor[] color) {
        if (horizontal) {
            for (int i = 0; i < 3; i++) {
                colors[line][i] = color[i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                colors[i][line] = color[i];
            }
        }
    }

    public FColor[] getColors(int line, boolean horizontal) {
        FColor[] returnColors = new FColor[3];
        if (horizontal) {
            for (int i = 0; i < 3; i++) {
                returnColors[i] = colors[line][i];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                returnColors[i] = colors[i][line];
            }
        }
        return returnColors;
    }

    public boolean isSolved() {
        FColor color = colors[1][1];
        for (int i= 0;i < 3;i++) {
            for (int j = 0;j < 3;j++) {
                if (colors[i][j] != color) return false;
            }
        }
        return true;
    }

    public void transpose(boolean clockWise) {
        FColor[][] newColors = new FColor[3][3];
        for (int i=0;i < 3;i++ ) {
            for (int j =0;j < 3;j++) {
                newColors[i][j] = colors[i][j];
            }
        }

        if (clockWise) {
            for (int i = 0; i < 3; i++) {
                colors[i][2] = newColors[0][i];
                colors[2][2 - i] = newColors[i][2];
                colors[i][0] = newColors[2][i];
                colors[0][2 - i] = newColors[i][0];
            }
        } else {
            for (int i = 0; i < 3; i++) {
                colors[0][i] = newColors[i][2];
                colors[2-i][0] = newColors[0][i];
                colors[2][i] = newColors[i][0];
                colors[i][2-i] = newColors[2][i];
            }
        }
    }

    public void print() {
        for (int i =0;i < 3;i++) {
            for (int j =0;j < 3;j++) {
                System.out.print(colors[i][j].ordinal()+" ");
            }
            System.out.println();
        }
    }

    public FaceType getType() {
        return type;
    }
    public int getScore() {
        int score = 0;
        FColor faceColor = colors[1][1];
        for (int i =0;i < 3;i++) {
            for (int j = 0;j < 3;j++) {
                if (colors[i][j] == faceColor) score++;
            }
        }
        return score;
    }


}


