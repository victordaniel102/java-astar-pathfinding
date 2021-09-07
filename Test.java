class Test {
    public static void main(String[] args) {
        Object[][] maze =  {{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        Pathfinding pathfinding = new Pathfinding(maze, new Position(0, 0), new Position(9,9));
        // pathfinding.maze();
        System.out.println("=".repeat(50));
        System.out.println(pathfinding.findPath());
        pathfinding.maze();
    }
}