package it.sesalab.minimaxgames.ai;

public abstract class MinimaxAI {
    public abstract Move findBestMove(Board currentBoard);

    protected static class ScoredMove {
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
