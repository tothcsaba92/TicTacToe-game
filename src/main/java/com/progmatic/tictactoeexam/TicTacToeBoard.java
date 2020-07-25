package com.progmatic.tictactoeexam;

import com.progmatic.tictactoeexam.enums.PlayerType;
import com.progmatic.tictactoeexam.exceptions.CellException;
import com.progmatic.tictactoeexam.interfaces.Board;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author csaba
 */

public class TicTacToeBoard implements Board {
    private Cell[][] cells = new Cell[3][3];

    public TicTacToeBoard() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    @Override
    public PlayerType getCell(int rowIdx, int colIdx) throws CellException {
        if (rowIdx < 0 || rowIdx > 2 || colIdx < 0 || colIdx > 2) {
            throw new CellException(rowIdx, colIdx, "rowIdx or colIdx points to a non-existent index");
        }
        return cells[rowIdx][colIdx].getCellsPlayer();
    }

    @Override
    public void put(Cell cell) throws CellException {
        if (cell.getRow() < 0 || cell.getRow() > 2 || cell.getCol() < 0 || cell.getCol() > 2) {
            throw new CellException(cell.getRow(), cell.getCol(), "rowIdx or colIdx points to a non-existent index");
        }
        if (cells[cell.getRow()][cell.getCol()].getCellsPlayer() != PlayerType.EMPTY) {
            throw new CellException(cell.getRow(), cell.getCol(), "cell is non empty");
        }
        cells[cell.getRow()][cell.getCol()] = cell;
    }

    @Override
    public boolean hasWon(PlayerType p) {

        int diagonalCnt = 0;
        int antiDiagonalCnt = 0;
        for (int i = 0; i < cells.length; i++) { //rows winning conditions covered
            int rowCnt = 0;
            int colCnt = 0;
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getCellsPlayer() == p) {
                    rowCnt++;
                }
                if (cells[j][i].getCellsPlayer() == p) {
                    colCnt++;
                }

                if (i == j && cells[i][j].getCellsPlayer() == p) {
                    diagonalCnt++;
                }
                if (rowCnt == 3 || colCnt == 3) {
                    return true;
                }
                if (i + j == cells.length - 1 && cells[i][j].getCellsPlayer() == p) {
                    antiDiagonalCnt++;
                }
            }
            if (diagonalCnt == 3 || antiDiagonalCnt == 3) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Cell> emptyCells() {
        List<Cell> emptyCells = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getCellsPlayer() == PlayerType.EMPTY) {
                    emptyCells.add(cells[i][j]);
                }
            }
        }
        return emptyCells;
    }
}
