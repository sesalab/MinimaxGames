package it.sesalab.minimaxgames.ai;

import java.util.List;

public class MinimaxAI {

    public Move findBestMove(Board currentBoard){
        return minimax(currentBoard, true).getMove();
    }

    protected ScoredMove minimax(Board currentBoard, boolean isMaximizingPlayer) {
        List<Move> nextMoves = currentBoard.generateNextMoves();
        int bestScore = isMaximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Move bestMove = null;

        if(nextMoves.isEmpty()){
            bestScore = currentBoard.utility();
        } else {
            for (Move move: nextMoves){
                move.execute();
                if(isMaximizingPlayer){
                    ScoredMove scoredMove = minimax(currentBoard, false);
                    if(scoredMove.getScore() > bestScore){
                        bestScore = scoredMove.getScore();
                        bestMove = move;
                    }


                } else {
                    ScoredMove scoredMove = minimax(currentBoard, true);
                    if(scoredMove.getScore() < bestScore){
                        bestScore = scoredMove.getScore();
                        bestMove = move;
                    }
                }


                move.undo();
            }
        }
        return new ScoredMove(bestMove, bestScore);
    }


    private static class ScoredMove {
        private final Move move;
        private final int score;

        public ScoredMove(Move move, int score) {
            this.move = move;
            this.score = score;
        }

        public Move getMove() {
            return move;
        }

        public int getScore() {
            return score;
        }
    }
}
