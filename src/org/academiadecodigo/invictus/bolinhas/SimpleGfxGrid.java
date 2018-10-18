package org.academiadecodigo.GroupProject1;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class SimpleGfxGrid {

    public static final int PADDING = 10;

    public static final int CELL_SIZE = 20;

    private static Rectangle screen;

    private int cols;
    private int rows;

    public SimpleGfxGrid(int cols, int rows) {

        this.cols = cols;
        this.rows = rows;

    }

    public void init() {

        // create the GUI and grid size
        this.screen = new Rectangle(PADDING, PADDING, cols * CELL_SIZE, rows * CELL_SIZE);

        // display the grid
        this.screen.setColor(Color.BLACK);
        this.screen.draw();

    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    /**
     * Obtains the width of the grid in pixels
     *
     * @return the width of the grid
     */
    public int getWidth() {
        return this.screen.getWidth();
    }

    /**
     * Obtains the height of the grid in pixels
     *
     * @return the height of the grid
     */
    public int getHeight() {
        return this.screen.getHeight();
    }

    /**
     * Obtains the grid X position in the SimpleGFX canvas
     *
     * @return the x position of the grid
     */
    public int getX() {
        return cols * CELL_SIZE + PADDING;
    }

    /**
     * Obtains the grid Y position in the SimpleGFX canvas
     *
     * @return the y position of the grid
     */
    public int getY() {
        return rows * CELL_SIZE + PADDING;
    }

    /**
     * Obtains the pixel width and height of a grid position
     *
     * @return
     */
    public int getCellSize() {
        return CELL_SIZE;
    }


    /**
     * Auxiliary method to compute the y value that corresponds to a specific row
     *
     * @param row index
     * @return y pixel value
     */
    public int rowToY(int row) {
        return row * CELL_SIZE + PADDING;
    }

    /**
     * Auxiliary method to compute the x value that corresponds to a specific column
     *
     * @param column index
     * @return x pixel value
     */
    public int columnToX(int column) {
        return column * CELL_SIZE + PADDING;
    }



}
