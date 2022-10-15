package main.sudoku.buildlogic;

import main.sudoku.computationlogic.GameLogic;
import main.sudoku.persistence.LocalStorageImpl;
import main.sudoku.problemdomain.IStorage;
import main.sudoku.problemdomain.SudokuGame;
import main.sudoku.userinterface.IUserInterfaceContract;
import main.sudoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {

    public static void build(IUserInterfaceContract.View userInterface) throws IOException
    {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try
        {
            initialState = storage.getGameData();
        }
        catch(IOException e)
        {
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);
        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);

    }
    
}
