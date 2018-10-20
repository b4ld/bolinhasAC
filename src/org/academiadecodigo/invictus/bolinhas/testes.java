/*


package org.academiadecodigo.invictus.bolinhas;

import java.awt.*;

public class Testes {

        public int rows, columns;
        public int x, y;
        private GemPool pool;
        private Gem[][] board;

        public Board(int rows, int columns) {

            x = 0;
            y = 0;

            this.rows = rows;
            this.columns = columns;

            board = new Gem[rows][columns];
            pool = new GemPool(COLOR.BLUE, COLOR.RED, COLOR.YELLOW, COLOR.GREEN, COLOR.ORANGE, COLOR.PURPLE);

            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < columns; j++) {
                    board[i][j] = pool.grabGem();
                }
            }

        }

        public Gem pointAt(int row, int column) {

            return board[row][column];

        }

        public void draw(SpriteBatch batch) {

            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < columns; j++) {
                    String color = board[i][j].getColorName();
                    //I'm aware of the magic numbers here
                    batch.draw(pool.grabTexture(color), (i * 72) + x, (j * 72) + y);
                }
            }

        }

        public void dispose() {

            pool.dispose();

        }

        public void click(Vector3 touchPos) {

            //I'm aware of the magic numbers here
            int x = (int)(touchPos.x / 72);
            int y = (int)(touchPos.y / 72);

            if(x < rows && y < columns) {
                Gem g = this.pointAt(x, y);

                HashSet<Point> matches = new HashSet<Point>();

                locateNeighbors(x, y, g.getColor(), matches);

                System.out.println(matches.size() + " matches found.");

            }

        }

        private void locateNeighbors(int x, int y, COLOR color, HashSet<Point> matches) {

            Point p = new Point(x, y);

            if(matches.contains(p))
            {
                return;
            }
            else
            {
                matches.add(p);
            }

            //Check north
            if(y + 1 < columns)
            {
                if(this.pointAt(x, y + 1).getColor() == color)
                    locateNeighbors(x, y + 1, color, matches);
            }

            //Check east
            if(x + 1 < rows)
            {
                if(this.pointAt(x + 1, y).getColor() == color)
                    locateNeighbors(x + 1, y, color, matches);
            }

            //Check south
            if(y - 1 >= 0)
            {
                if(this.pointAt(x, y - 1).getColor() == color)
                    locateNeighbors(x, y - 1, color, matches);
            }

            //Check west
            if(x - 1 >= 0)
            {
                if(this.pointAt(x - 1, y).getColor() == color)
                    locateNeighbors(x - 1, y, color, matches);
            }

        }


}

*/
