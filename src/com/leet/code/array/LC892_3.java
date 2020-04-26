package com.leet.code.array;

public class LC892_3 {
    public static void main(String[] args) {
        LC892_3 solution = new LC892_3();
        // int grid[][] = {{1,2},{3,4}};
        int grid[][] = {{1,0},{0,2}};
        System.out.println(solution.surfaceArea(grid));
    }
    // 柱体的面 = 块数 * 4 + 2
    // 相邻面对柱体面的影响：减少面数 = min(两个柱体高度)
    public int surfaceArea(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                res += grid[i][j]>0 ? (grid[i][j] * 4 + 2) : 0;
                if (i > 0){
                    res -= Math.min(grid[i-1][j], grid[i][j]) * 2;
                }
                if (j > 0){
                    res -= Math.min(grid[i][j-1], grid[i][j]) * 2;
                }
            }
        }
        return res;
    }


    // 柱体的面数 = (方块数量 * 4 + 2)
    // 重合面数量 = min(grid[0] ... grid[n])，有行、列两个方向
    private int surfaceArea2(int[][] grid) {
        int res = 0;
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0){
                    res += grid[i][j] * 4 + 2;
                }
                // 后面的往前比较，第0行、0列不做处理
                // 另一种思路是：前面向后比
                if (i > 0){
                    res -= Math.min(grid[i-1][j], grid[i][j]) * 2;
                }
                if (j > 0){
                    res -= Math.min(grid[i][j-1], grid[i][j]) * 2;
                }
            }
        }
        return res;
    }

    private int surfaceArea1(int[][] grid) {
        if (grid.length == 0){
            return 0;
        }
        int num = 0;
        // 柱体的面数 = (方块数量 * 4 + 2)
        // 重合面数量 = min(grid[0] ... grid[n])，有行、列两个方向
        int row = grid.length;
        int col = grid[0].length;
        // 考察对二维数据的操作，基于一个元素的位置，访问它的相邻的两个元素
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 有方块是才能按这个公式计算
                // if (grid[i][j] > 0){
                //     num += grid[i][j] * 4 + 2;
                // }
                num += grid[i][j]>0 ? (grid[i][j] * 4 + 2) : 0;
                // 行：i后面还有方块
                if (i + 1 < row){
                    num -= Math.min(grid[i][j], grid[i+1][j]) * 2;
                }
                // 列：i上面还有方块
                if (j + 1 < col){
                    num -= Math.min(grid[i][j], grid[i][j+1]) * 2;
                }
            }
        }
        return num;
    }
}
