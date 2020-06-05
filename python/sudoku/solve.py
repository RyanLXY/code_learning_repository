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

board_2 = [
    [7, 8, 0, 4, 0, 0, 1, 2, 0],
    [6, 0, 0, 0, 7, 5, 0, 0, 9],
    [0, 0, 0, 6, 0, 1, 0, 7, 8],
    [0, 0, 7, 0, 4, 0, 2, 6, 0],
    [0, 0, 1, 0, 5, 0, 9, 3, 0],
    [9, 0, 4, 0, 6, 0, 0, 0, 5],
    [0, 7, 0, 3, 0, 0, 0, 1, 2],
    [1, 2, 0, 0, 0, 7, 4, 0, 0],
    [0, 4, 9, 2, 0, 6, 0, 0, 7]
]


def print_board(input_board):
    print("* - - - - - - - - - - - - *")
    for i in range(len(input_board)):
        if i % 3 == 0 and i != 0:
            print("| - - - - - - - - - - - - |")

        for j in range(len(board[0])):
            if j % 3 == 0 and j != 0:
                print(" | ", end="")

            if j == 0:
                print("| " + str(input_board[i][j]), end=" ")
            elif j == 8:
                print(str(input_board[i][j]) + " |")
            else:
                print(str(input_board[i][j]) + " ", end="")

    print("* - - - - - - - - - - - - *")


# # print_board(board)
def find_empty(input_board):
    for i in range(len(input_board)):
        for j in range(len(input_board[0])):
            if input_board[i][j] == 0:
                # print(i, j)
                return (i, j)
    return None


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


def solve(input_board):
    find = find_empty(input_board)
    if not find:
        return True
    else:
        row, col = find

    for i in range(1, 10):
        if check_valid(input_board, i, (row, col)):
            input_board[row][col] = i

            # Backtracking algorithm
            if solve(input_board):
                return True

            input_board[row][col] = 0

    return False


print_board(board)
solve(board)
print_board(board)
