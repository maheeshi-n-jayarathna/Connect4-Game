package lk.ijse.connect4game.service;

public interface BoardUI {
    void update(int col, boolean isHuman);

    void notifyWinner(Winner winner);
}
