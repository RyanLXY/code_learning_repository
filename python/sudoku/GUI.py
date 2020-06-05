import pygame

class Grid:
    board = [
        [5, 3, 0, 0, 7, 0, 0, 0, 0],
        [6, 0, 0, 1, 9, 5, 0, 0, 0],
        [0, 9, 8, 0, 0, 0, 0, 6, 0],
        [8, 0, 0, 0, 6, 0, 0, 0, 3],
        [4, 0, 0, 8, 0, 3, 0, 0, 1],
        [7, 0, 0, 0, 2, 0, 0, 0, 6],
        [0, 6, 0, 0, 0, 0, 2, 8, 0],
        [0, 0, 0, 4, 1, 9, 0, 0, 5],
        [0, 0, 0, 0, 8, 0, 0, 7, 9]
    ]

    def __init__(self, rows, cols, width, height, win):
        self.rows = rows
        self.cols = cols
        self.cubes = [[Cube(self.board[i][j], i, j, width, height) for j in range(cols)] for i in range(rows)]
        self.width = width
        self.height = height
        self.model = None
        self.update_model()
        self.selected = None
        self.win = win

def check_valid(input_board, num, position):
    # check row
    for i in range(0, len(input_board)):
        if input_board[position[0]][i] == num and position[1] != i:
            return False

    # check column
    for i in range(len(input_board)):
        if input_board[i][position[1]] == num and position[0] != i:
            return False

    # check box
    # [00] [01] [02]
    # [10] [11] [12]
    # [20] [21] [22]
    box_x = position[1] // 3
    box_y = position[0] // 3
    # print(box_x,box_y)

    for i in range(box_y * 3, box_y * 3 + 3):
        for j in range(box_x * 3, box_x * 3 + 3):
            if input_board[i][j] == num and (i, j) != position:
                return False

    return True

class Cube:
    rows = 9
    cols = 9

    def __init__(self):