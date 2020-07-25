package com.progmatic.tictactoeexam;

import com.progmatic.tictactoeexam.enums.PlayerType;
import com.progmatic.tictactoeexam.exceptions.CellException;
import com.progmatic.tictactoeexam.interfaces.AbstractPlayer;
import com.progmatic.tictactoeexam.interfaces.Board;

/**
 *
 * @author csaba
 */

public class SimplePlayer extends AbstractPlayer {
    public SimplePlayer(PlayerType p) {
        super(p);
    }

    @Override
    public Cell nextMove(Board b) throws CellException {
        if(b.emptyCells().isEmpty()){
            return null;
        }
        return new Cell(b.emptyCells().get(0).getRow(),b.emptyCells().get(0).getCol(),myType);
    }
}
