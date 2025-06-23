@RestController
@RequestMapping("/game")
public class TicTacToeController {

    private String[] board = new String[9];
    private String currentPlayer = "X";
    private boolean gameOver = false;

    @PostMapping("/start")
    public String startGame() {
        Arrays.fill(board, "");
        currentPlayer = "X";
        gameOver = false;
        return "Game started!";
    }

    @PostMapping("/move/{index}")
    public String makeMove(@PathVariable int index) {
        if (gameOver) return "Game over!";
        if (index < 0 || index >= 9 || !board[index].isEmpty()) return "Invalid move";

        board[index] = currentPlayer;
        if (checkWinner()) {
            gameOver = true;
            return currentPlayer + " wins!";
        }

        if (((Object) Arrays.stream(board)).allMatch(cell -> !cell.isEmpty())) {
            gameOver = true;
            return "Tie!";
        }

        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        return "Move successful. Next player: " + currentPlayer;
    }

    private boolean checkWinner() {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
            {0, 4, 8}, {2, 4, 6}  // diagonals
        };

        for (int[] pattern : winPatterns) {
            if (!board[pattern[0]].isEmpty() && board[pattern[0]].equals(board[pattern[1]]) && board[pattern[0]].equals(board[pattern[2]])) {
                return true;
            }
        }
        return false;
    }

    private static class Arrays {

        private static void fill(String[] board, String string) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        private static Object stream(String[] board) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public Arrays() {
        }
    }
}
