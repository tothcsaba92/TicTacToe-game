package com.progmatic.tictactoeexam;

import com.progmatic.tictactoeexam.enums.PlayerType;
import com.progmatic.tictactoeexam.exceptions.CellException;
import com.progmatic.tictactoeexam.interfaces.AbstractPlayer;
import com.progmatic.tictactoeexam.interfaces.Board;

import java.util.List;

/**
 *
 * @author csaba
 */

public class VictoryAwarePlayer extends AbstractPlayer {
    public VictoryAwarePlayer(PlayerType p) {
        super(p);
    }

    @Override
    public Cell nextMove(Board b) throws CellException {
        for (Cell emptyCell : b.emptyCells()) {
            Board myBoard = new TicTacToeBoard();
            for (int i = 0; i < 3; i++) {       //lemasolom a b board tartalmat a myboardba
                for (int j = 0; j < 3; j++) {
                    myBoard.put(new Cell(i,j,b.getCell(i,j)));
                }
            }
            Cell myCell = new Cell(emptyCell.getRow(),emptyCell.getCol(),myType); //letrehozok egy cellat az urescellak alapjan
            myBoard.put(myCell);
            if(myBoard.hasWon(myType)){
                return myCell;
            }
        }
        List<Cell> cells = b.emptyCells();// ha nincs ures hely akkor null-al visszater
        if(cells.isEmpty()){
            return null;
        }
        Cell loserCell = new Cell(cells.get(0).getRow(),cells.get(0).getCol(),myType); //ha nincs nyero pozicio,akkor az elso helyre lep
        //az emptyCells-bol,ugyanugy mint  a simpleplayer!

        return loserCell;
    }
}
