import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pathfinding {
    Object[][] maze;
    Node start;
    Node goal;

    ArrayList<Node> open = new ArrayList<Node>();
    ArrayList<Node> closed = new ArrayList<Node>();

    public Pathfinding(Object[][] maze, Position start, Position goal) {
        this.maze = maze;
        
        this.maze[start.x][start.y] = 2;
        this.maze[goal.x][goal.y] = 2;
    
        
        mapMaze();
        this.start = (Node)maze[start.x][start.y];
        this.goal = (Node)maze[goal.x][goal.y];
    }

    public void mapMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = new Node(new Position(i, j), maze[i][j]);
            }
        }

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                Node node = (Node)maze[i][j];
                node.setNeighbors(maze);
            }
        }
    }

    public ArrayList<Position> findPath() {
        open.add(start);
        
        while (open.size() > 0) {
            Node current = (Node)open.get(0);
            int current_index = 0;

            for (Node node : open) {
                if (node.f < current.f) {
                    current = node;
                    current_index = open.indexOf(node);
                }
            }
    
            open.remove(current_index);
            closed.add(current);

            if (current.position.x == goal.position.x && current.position.y == goal.position.y) {
                return reconstructPath(current);
            }
            
            closed.add(current);
            for (Node neighbor : current.neighbors) {
                if (!closed.contains(neighbor)) {
                    // neighbor.g = current.g + 4;
                    neighbor.h = (int)Math.pow(neighbor.position.x - goal.position.x, 2) + (int)Math.pow(neighbor.position.y - goal.position.y, 2);
                    neighbor.f = neighbor.g + neighbor.h;

                    for (Node node : open)
                        if (neighbor == node && neighbor.g > node.g) break;

                    neighbor.parent = current;
                    open.add(neighbor);
                    System.out.println("size:" + open.size());
                }
            }
        }

        return null;
    }

    private ArrayList<Position> reconstructPath(Node current) {
        ArrayList<Position> path = new ArrayList<Position>();
        while (current.parent != null) {
            current.value = 3;
            path.add(current.position);
            current = current.parent;
        }

        return path;
    }

    class Node {
        Node parent;
        Object value;
        ArrayList<Node> neighbors = new ArrayList<Node>();
        Position position;
        int g = 10, h = 0, f = 0;

        public int getF() {
            return f;
        }

        public Node(Position position, Object value) {
            this.position = position;
            this.value = value;
        }

        public Object value() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public void setNeighbors(Object[][] maze) {
            List<Position> positions = Arrays.asList(new Position(0, -1), new Position(0, 1), new Position(-1, 0), new Position(1, 0), new Position(-1, -1), new Position(-1, 1), new Position(1, -1), new Position(1, 1));
        
            positions.forEach(p -> {
                Position node_position = new Position(position.x + p.x, position.y + p.y);

                if (!(node_position.x > (maze[0].length - 1) || node_position.x < 0 || node_position.y > (maze.length -1) || node_position.y < 0)) {
                    Node node = (Node)maze[node_position.x][node_position.y];
                    if ((int)node.value() != 1) {
                        if ((p.x == -1 && p.y == -1) || (p.x == 1 && p.y == 1) || (p.x == -1 && p.y == 1) || (p.x == 1 && p.y == -1))
                            node.g = 14;

                        this.neighbors.add(node);
                    }
                }
            });
        }
    }

    public void maze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                Node node = (Node)maze[i][j];
                System.out.print(node.value() + " ");
            }
            System.out.println();
        }
    }
}

class Position {
    int x;
    int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
