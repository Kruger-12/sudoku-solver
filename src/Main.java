import java.util.Arrays;

public class Main {
	
	// Creating the Sudoku-grid
	/*
	 * Grid is invalid if the sqrt of its length is not an integer
	 * !!! Other grid-sizes than 9x9 (e.g. 16x16, 25x25) aren't tested !!!
	 * 
	 */	
	private static int[][] grid = {
			{0, 4, 0, 0, 0, 8, 0, 1, 0},
	        {0, 0, 0, 3, 0, 0, 0, 0, 0},
	        {0, 0, 8, 0, 9, 0, 0, 5, 0},
	        {0, 0, 0, 0, 0, 0, 3, 0, 5},
	        {9, 0, 0, 0, 5, 0, 7, 2, 0},
	        {6, 2, 0, 0, 7, 0, 0, 9, 0},
	        {0, 5, 9, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 2, 1, 0, 0, 0, 0},
	        {0, 0, 0, 6, 0, 0, 4, 0, 0}
    };
    
    private static int subgridSize;
	
	private static boolean done = false;
	
	public static void main(String[] args) {		
		start(grid);	
    }
    
    private static void start(int[][] grid) {
        // Check if the grid is valid
        if(!isPerfectSquare(grid.length)) {
            System.out.println("Grid is not valid!");
        }else{
            subgridSize = (int) Math.sqrt(grid.length);
            solveGrid(grid);
        }
    }

    private static boolean isPerfectSquare(int num) {

        double sqrt = Math.sqrt(num);
        return ((sqrt - Math.floor(sqrt)) == 0);
    }
	
	// Check if the number N is valid at the given X-Y-position
	private static boolean isValid(int x, int y, int n) {
		
		// Check if N already exists in the row/column
		for(int i = 0; i < grid.length; i++) {		
			if(grid[i][x] == n || grid[y][i] == n)
				return false;
		}
		
		int subgridX = (int) ((x / subgridSize) * subgridSize);
		int subgridY = (int) ((y / subgridSize) * subgridSize);
		
		// Check if N already exists in it's subgrid
		for(int i = 0; i < subgridSize; i++) {
			for(int j = 0; j < subgridSize; j++) {
				
				if(grid[subgridY + i][subgridX + j] == n)
					return false;
				
			}
		}
		
		return true;
		
    }
    
    private static boolean isEmpty(int[][] grid, int x, int y) {

        return grid[y][x] == 0;

    }
	
	private static void solveGrid(int[][] grid) {
		
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid.length; y++) {
				
				// Check if the field is empty (0)
				if(isEmpty(grid, x, y)) {
					for(int n = 1; n <= grid.length; n++) {
						
						if(isValid(x, y, n)) {
							grid[y][x] = n;
							solveGrid(grid);
							grid[y][x] = 0;
						}
						
					}
					
					// If every number N is invalid at (x, y = 0, 0) the grid is impossible to solve
					if(x == 0 && y == 0 && !done)
						System.out.println("Error: grid cannot be solved");
					return;
					
				}
				
			}
		}
		
		// Print the solved grid to the console
		for(int i = 0; i < grid.length; i++) 
			System.out.println(Arrays.toString(grid[i]));
		done = true;
		
	}

}