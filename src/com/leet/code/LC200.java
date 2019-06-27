package com.leet.code;


import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC200 {

    // 选中一个点，深度遍历所有值为1的点
    // 方向：每行从左到右，从上到下，直到遇到为0的点
    // 访问过的点都标记为“已扫描”，如果该点未扫描过，说明是新的岛屿
    public int numIslands(char[][] grid) {
        int num = 0;
        // 遍历每个点
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 已访问过的点（标记为x）直接跳过
                // 未访问过的点是新的岛屿
                if (grid[i][j] != 'x' && grid[i][j] != '0'){
                    num ++;
                    grid[i][j] = 'x';
                    // DFS 或 BFS
                    dfs(grid, i, j);
                    // bfs(grid, i, j);
                }
            }
        }

        return num;
    }


    // 执行用时 : 4 ms
    // 内存消耗 : 40.8 MB
    // 把该点相邻的点记录下来，作为第二层遍历的点
    public void bfs(char[][] array, int row, int col){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {row, col});

        // 四个方向的相邻的，且为1的点入队列
        while (!queue.isEmpty()){
            int[] node = queue.poll();
            row = node[0];
            col = node[1];
            // 左
            if (col - 1 >= 0 && array[row][col-1] == '1'){
                queue.add(new int[] {row, col-1});
                array[row][col-1] = 'x';
            }
            // 右
            if (col + 1 < array[0].length && array[row][col+1] == '1'){
                queue.add(new int[] {row, col+1});
                array[row][col+1] = 'x';
            }
            // 上
            if (row - 1 >= 0 && array[row-1][col] == '1'){
                queue.add(new int[] {row-1, col});
                array[row-1][col] = 'x';
            }
            // 下
            if (row + 1 < array.length && array[row+1][col] == '1'){
                queue.add(new int[] {row+1, col});
                array[row+1][col] = 'x';
            }
        }
    }


    // 执行用时 : 4 ms
    // 内存消耗 : 42.8 MB
    // 等于1就递归调用dfs
    // 前后左右4个方向遍历，直到不能前进才return
    public void dfs(char[][] array, int row, int col){
        // 左
        if (col - 1 >= 0 && array[row][col-1] == '1'){
            array[row][col-1] = 'x';
            dfs(array, row, col-1);
        }
        // 向右
        if (col + 1 < array[0].length && array[row][col+1] == '1'){
            array[row][col+1] = 'x';
            dfs(array, row, col+1);
        }
        // 上
        if (row - 1 >= 0 && array[row-1][col] == '1'){
            array[row-1][col] = 'x';
            dfs(array, row-1, col);
        }
        // 下
        if (row + 1 < array.length && array[row+1][col] == '1'){
            array[row+1][col] = 'x';
            dfs(array, row+1, col);
        }
    }



    public static void main(String[] args) {
        LC200 solution = new LC200();

        // char[][] grid = {{'1','1','1'},{'0','1','0'},{'1','1','1'}};
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(solution.numIslands(grid));
    }



}
