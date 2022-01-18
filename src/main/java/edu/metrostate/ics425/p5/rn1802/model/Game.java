package edu.metrostate.ics425.p5.rn1802.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Ricky N Game Class that handles data and the logic of Reversi
 */
public class Game implements Serializable {

	private static final long serialVersionUID = 20211119001L;
	static final int NUM_SPACES = 64;
	private String[] grid;
	private boolean[] valid;
	private int turn;
	private int skips;

	/**
	 * initializes a new game instance
	 */
	public Game() {
		grid = new String[NUM_SPACES];
		valid = new boolean[NUM_SPACES];
		turn = 0;
		grid[27] = "L";
		grid[28] = "D";
		grid[35] = "D";
		grid[36] = "L";
		skips = 0;
	}

	/**
	 * @return amount of dark marks
	 */
	public int getDarkCount() {
		int count = 0;
		for (int i = 0; i < NUM_SPACES; i++) {
			if (grid[i] == "D") {
				count++;
			}
		}
		return count;
	}

	/**
	 * @return amount of light marks
	 */
	public int getLightCount() {
		int count = 0;
		for (int i = 0; i < NUM_SPACES; i++) {
			if (grid[i] == "L") {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns the current player if game is still in play
	 * 
	 * @return the current player
	 */
	public String getCurrentPlayer() {
		return turn % 2 == 0 ? "D" : "L";

	}
	
	/*
	 * @return mark at location
	 */
	public String getMark(int loc) {
		return grid[loc];
	}

	/*
	 * if the game is not over and the location is a valid move, place mark,
	 * increment turn and return true else if not valid move return false
	 * 
	 * @return if mark was placed
	 */
	public boolean placeMark(int loc) {
		boolean placed = false;

		if (isEmptySpot(loc)) {
			flipMarks(loc);
			placed = true;
			turn++;
		}

		return placed;
	}

	/*
	 * flips all marks from location to closest player mark
	 */
	private void flipMarks(int loc) {
		String playerMark = getCurrentPlayer();
		int nextMark;
		grid[loc] = playerMark;

		// checks if there is a valid move in a direction and flips them
		if (checkRight(playerMark, loc)) {
			nextMark = loc + 1;
			while (nextMark % 8 < 8 && nextMark < 64) {
				if (grid[nextMark] == playerMark) {
					break;
				} else if (grid[nextMark] != playerMark) {
					grid[nextMark] = playerMark;
					nextMark++;
				}
			}
		}

		if (checkLeft(playerMark, loc)) {
			nextMark = loc - 1;
			while (nextMark % 8 > 0 && nextMark > 0) {
				if (grid[nextMark] == playerMark) {
					break;
				} else if (grid[nextMark] != playerMark) {
					grid[nextMark] = playerMark;
					nextMark--;
				}
			}
		}

		if (checkUp(playerMark, loc)) {
			nextMark = loc - 8;
			while (nextMark % 8 > 0 && nextMark > 0) {
				if (grid[nextMark] == playerMark) {
					break;
				} else if (grid[nextMark] != playerMark) {
					grid[nextMark] = playerMark;
					nextMark -= 8;
				}
			}
		}

		if (checkDown(playerMark, loc)) {
			nextMark = loc + 8;
			while (nextMark / 8 < 7 && nextMark < 63) {
				if (grid[nextMark] == playerMark) {
					break;
				} else if (grid[nextMark] != playerMark) {
					grid[nextMark] = playerMark;
					nextMark += 8;
				}
			}
		}

		if (checkUpLeft(playerMark, loc)) {
			nextMark = loc - 9;
			while (nextMark / 8 > 0 && nextMark % 8 > 0) {
				if (grid[nextMark] == playerMark) {
					break;
				} else if (grid[nextMark] != playerMark) {
					grid[nextMark] = playerMark;
					nextMark -= 9;
				}
			}
		}

		if (checkUpRight(playerMark, loc)) {
			nextMark = loc - 7;
			while (nextMark / 8 > 0 && nextMark % 8 < 7) {
				if (grid[nextMark] == playerMark) {
					break;
				} else if (grid[nextMark] != playerMark) {
					grid[nextMark] = playerMark;
					nextMark -= 7;
				}
			}
		}

		if (checkDownLeft(playerMark, loc)) {
			nextMark = loc + 7;
			while (nextMark / 8 < 7 && nextMark % 8 > 0) {
				if (grid[nextMark] == playerMark) {
					break;
				} else if (grid[nextMark] != playerMark) {
					grid[nextMark] = playerMark;
					nextMark += 7;
				}
			}
		}

		if (checkDownRight(playerMark, loc)) {
			nextMark = loc + 9;
			while (nextMark / 8 < 7 && nextMark % 8 < 7) {
				if (grid[nextMark] == playerMark) {
					break;
				} else if (grid[nextMark] != playerMark) {
					grid[nextMark] = playerMark;
					nextMark += 9;
				}
			}
		}
	}

	/*
	 * @return true if the location is on the board and is empty
	 * 
	 */
	public boolean isEmptySpot(int loc) {
		return isOnBoard(loc) && isEmpty(loc);
	}

	/*
	 * @return true if there is a match to the right
	 */
	private boolean checkRight(String playerMark, int loc) {
		int nextMark = loc + 1;

		// if the mark is on the far right if cannot be placed to the right
		if (loc % 8 == 7) {
			return false;
		}
		// if the mark directly next to loc is same color unplayable
		if (grid[nextMark] == playerMark)
			return false;

		// checks pieces to the right
		while (nextMark % 8 < 8 && nextMark < NUM_SPACES) {
			if (grid[nextMark] != null) {
				if (grid[nextMark] == playerMark) {
					return true;
				} else {
					nextMark++;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * @return true if there is a match to the left
	 */
	private boolean checkLeft(String playerMark, int loc) {
		int nextMark = loc - 1;

		// if the mark is on the far left if cannot be placed to the left
		if (loc % 8 == 0) {
			return false;
		}
		// if the mark directly next to loc is same color unplayable
		if (grid[nextMark] == playerMark)
			return false;

		// checks pieces to the left
		while (nextMark % 8 > 0 && nextMark > 0) {
			if (grid[nextMark] != null) {
				if (grid[nextMark] == playerMark) {
					return true;
				} else {
					nextMark--;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * @return true if there is a match below
	 */
	private boolean checkDown(String playerMark, int loc) {
		int nextMark = loc + 8;

		// if the mark is on the bottom if cannot be placed below
		if (loc / 8 == 7) {
			return false;
		}
		// if the mark directly below loc is same color then it is unplayable
		if (grid[nextMark] == playerMark)
			return false;

		// checks pieces below
		while (nextMark / 8 < 7 && nextMark < NUM_SPACES) {
			if (grid[nextMark] != null) {
				if (grid[nextMark] == playerMark) {
					return true;
				} else {
					nextMark += 8;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * @return true if there is a match above
	 */
	private boolean checkUp(String playerMark, int loc) {
		int nextMark = loc - 8;

		// if the mark is at the top if cannot be placed above
		if (loc / 8 == 0) {
			return false;
		}
		// if the mark directly above loc is same color then it is unplayable
		if (grid[nextMark] == playerMark)
			return false;

		// checks pieces above
		while (nextMark / 8 > 0 && nextMark > 0) {
			if (grid[nextMark] != null) {
				if (grid[nextMark] == playerMark) {
					return true;
				} else {
					nextMark -= 8;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * @return true if there is a match up and to the left
	 */
	private boolean checkUpLeft(String playerMark, int loc) {
		int nextMark = loc - 9;

		// if the mark is at the top if cannot be placed above or to the leftmost
		if (loc / 8 == 0 || loc % 8 == 0) {
			return false;
		}
		// if the mark directly above and to the left of loc is same color then it is
		// unplayable
		if (nextMark < 0 || grid[nextMark] == playerMark)
			return false;

		// checks pieces above
		while (nextMark / 8 > 0 && nextMark > 0 && nextMark % 8 > 0) {
			if (grid[nextMark] != null) {
				if (grid[nextMark] == playerMark) {
					return true;
				} else {
					nextMark -= 9;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * @return true if there is a match up and to the left
	 */
	private boolean checkUpRight(String playerMark, int loc) {
		int nextMark = loc - 7;

		// if the mark is at the top if cannot be placed above
		if (loc / 8 == 0 || loc % 8 == 7) {
			return false;
		}
		// if the mark directly above and to the right of loc is same color then it is
		// unplayable
		if (grid[nextMark] == playerMark)
			return false;

		// checks pieces above
		while (nextMark / 8 > 0 && nextMark % 8 < 7 && nextMark > 0) {
			if (grid[nextMark] != null) {
				if (grid[nextMark] == playerMark) {
					return true;
				} else {
					nextMark -= 7;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * @return true if there is a match below and to the left
	 */
	private boolean checkDownLeft(String playerMark, int loc) {
		int nextMark = loc + 7;

		// if the mark is on the bottom if cannot be placed below or to the left
		if (loc / 8 == 7 || loc % 8 == 0) {
			return false;
		}
		// if the mark directly below loc is same color then it is unplayable
		if (grid[nextMark] == playerMark)
			return false;

		// checks pieces diagonally down left
		while (nextMark / 8 < 7 && nextMark % 8 > 0 && nextMark < NUM_SPACES) {
			if (grid[nextMark] != null) {
				if (grid[nextMark] == playerMark) {
					return true;
				} else {
					nextMark += 7;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * @return true if there is a match below to the right
	 */
	private boolean checkDownRight(String playerMark, int loc) {
		int nextMark = loc + 9;

		// if the mark is on the bottom or to the right if cannot be placed
		if (loc / 8 == 7 || loc % 8 == 7) {
			return false;
		}
		// if the mark directly below loc is same color then it is unplayable
		if (grid[nextMark] == playerMark)
			return false;

		// checks pieces below
		while (nextMark / 8 < 7 && nextMark % 8 < 7 && nextMark < NUM_SPACES) {
			if (grid[nextMark] != null) {
				if (grid[nextMark] == playerMark) {
					return true;
				} else {
					nextMark += 9;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	/*
	 * @return True if the loc is on the board
	 */
	private boolean isOnBoard(int loc) {
		return loc >= 0 && loc < NUM_SPACES;
	}

	/*
	 * @return True if the loc on the board is null
	 */
	private boolean isEmpty(int loc) {
		return grid[loc] == null;
	}

	/*
	 * @return a copy of the playing grid
	 */
	public String[] getGrid() {
		return grid.clone();
	}

	/*
	 * @return a copy of the valid grid
	 */
	public boolean[] getValid() {
		return valid.clone();
	}

	/*
	 * @return true if there exists at least one straight (horizontal, vertical, or
	 * diagonal) occupied line between the new piece and another dark piece, with
	 * one or more contiguous light pieces between them. set all valid spaces to
	 * true.
	 * 
	 */

	public boolean setValid() {
		Arrays.fill(valid, false);
		String playerMark = getCurrentPlayer();
		boolean moves = false;

		for (int i = 0; i < NUM_SPACES; i++) {
			if (grid[i] == null) {
				if (isEmptySpot(i) && (checkRight(playerMark, i) || checkLeft(playerMark, i) || checkDown(playerMark, i)
						|| checkUp(playerMark, i) || checkUpLeft(playerMark, i) || checkUpRight(playerMark, i)
						|| checkDownLeft(playerMark, i) || checkDownRight(playerMark, i))) {
					valid[i] = true;
					moves = true;
					skips = 0;
				}
			}
		}

		if (!moves) {
			skips++;
			turn++;
		}
		return moves;

	}

	public int getSkips() {
		return skips;
	}

	/*
	 * @return the {@link Marks} of the winner or draw if no winner
	 */

	public String getWinner() {
		if (isWon()) {
			return getDarkCount() > getLightCount() ? "Dark Wins" : "Light Wins";
		} else
			return "Draw";
	}

	/*
	 * when the game is over and there is a winner return true else false
	 * 
	 * @return true if there is a winner
	 */
	private boolean isWon() {
		if (getDarkCount() == getLightCount())
			return false;
		return true;
	}

	/*
	 * resets values to default
	 */
	public void restart() {
		grid = new String[NUM_SPACES];
		turn = 0;
		grid[27] = "L";
		grid[28] = "D";
		grid[35] = "D";
		grid[36] = "L";
	}

}
