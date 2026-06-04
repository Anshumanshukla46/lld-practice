package q7_wip_snakeNladder;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    Cell[][] cells;

    // CAN TAKE USER INPUT AS WELL
    public Board(int boardSize, int numberOfSnakes, int numOfLadders) {
        initialiseCells(boardSize);
        addSnakesAndLadders(cells, numberOfSnakes, numOfLadders);
    }

    private void initialiseCells(int boardSize) {
        cells = new Cell[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Cell cellObj = new Cell();
                cells[i][j] = cellObj;
            }
        }
    }


    private void addSnakesAndLadders(Cell[][] cells, int numberOfSnakes, int numOfLadders) {
        int n = cells.length;

        while (numberOfSnakes > 0) {
            int snakeHead = ThreadLocalRandom.current().nextInt(1, n * n - 1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, n * n - 1);

            if (snakeTail >= snakeHead) { // for snake head will be at higher number than tail
                continue;
            }

            Jump snakeObj = new Jump();
            snakeObj.start = snakeHead;
            snakeObj.end = snakeTail;

            Cell cell = getCell(snakeHead);
            cell.jump = snakeObj;

            numberOfSnakes--;
        }


        while (numOfLadders > 0) {
            int ladderStart = ThreadLocalRandom.current().nextInt(1, n * n - 1);
            int ladderEnd = ThreadLocalRandom.current().nextInt(1, n * n - 1);

            if (ladderStart >= ladderEnd) { // ladder will have head at lower number than tail
                continue;
            }

            Jump snakeObj = new Jump();
            snakeObj.start = ladderStart;
            snakeObj.end = ladderEnd;

            Cell cell = getCell(ladderStart);
            cell.jump = snakeObj;

            numOfLadders--;
        }
    }

    public Cell getCell(int playerPosition) {
        int boardRow = playerPosition / cells.length;
        int boardColumn = playerPosition % cells.length;

        return cells[boardRow][boardColumn];
    }
}
