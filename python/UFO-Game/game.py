import turtle
import random
import simpleaudio as sa

# 引入音乐
laser = sa.WaveObject.from_wave_file('laser.wav')
explosion = sa.WaveObject.from_wave_file('explosion.wav')

# 设置背景
game = turtle.Screen()
game.setup(640, 640)
game.title('UFO-Game')
game.bgpic('bg.gif')

# 创建玩家
turtle.addshape('player.gif')
player = turtle.Turtle()
player.speed(0)  # 设置画笔速度为瞬间移动到指定位置
player.up()  # 画笔抬起 去掉两点之间的线
player.shape('player.gif')
player.setpos(0, -280)
player.st()
player_step = 10


# go_left
def go_left():
    x = player.xcor()
    x = x - player_step
    if x < - 300:
        x = -300
    player.setx(x)


# go_right
def go_right():
    x = player.xcor()
    x = x + player_step
    if x > 300:
        x = 300
    player.setx(x)


# 移动玩家
turtle.listen()
turtle.onkey(go_left, 'Left')
turtle.onkey(go_right, 'Right')


# 添加子弹
bomb = turtle.Turtle()
bomb.ht()
bomb.speed(0)
bomb.up()
bomb.shape('triangle')
bomb.color('yellow')
bomb.shapesize(0.5, 0.5)
bomb.seth(90)

# 添加分数
score = 0
pen = turtle.Turtle()
pen.color('white')
pen.speed(0)
pen.up()
pen.setpos(-320, 300)
score_string = "Score: %s" %score
pen.write(score_string, align='left', font=('Arial', 16, 'normal'))

# 发射子弹
is_fired = False


def fire():
    global is_fired  # 此处声明函数内变量使用全局变量
    if not is_fired and not game_over:
        laser.play()
        bomb.setpos(player.xcor(), player.ycor() + 20)
        bomb.st()
        is_fired = True


turtle.onkey(fire, 'space')

# 添加敌人
num = 6
inv_list = []
turtle.addshape('inv.gif')
for i in range(num):
    inv = turtle.Turtle()
    inv_list.append(inv)
    inv.ht()
    inv.speed(0)
    inv.up()
    inv.shape('inv.gif')
    x = random.randint(-10, 10) * 20
    y = random.randint(5, 11) * 20
    inv.setpos(x, y)
    inv.st()

# 敌人移动
inv_step = 2
go_back = False
bomb_step = 20
game_over = False
while True:
    if game_over:
        sign = turtle.Turtle()
        sign.color('red')
        sign.ht()
        sign.write("Game Over", align='center', font=('Arial', 20, 'bold'))
        break
    for inv in inv_list:
        x = inv.xcor()
        x += inv_step
        inv.setx(x)
        if x > 280 or x < - 280:
            go_back = True

        if inv.distance(bomb) < 15:
            explosion.play()
            inv.setpos(0, 300)
            is_fired = False
            bomb.setpos(-320, -320)
            bomb.ht()
            score += 10
            score_string = "Score: %s" % score
            pen.clear()
            pen.write(score_string, align='left', font=('Arial', 16, 'normal'))

        if inv.ycor() < -250:
            game_over = True

    if go_back:
        inv_step *= -1
        go_back = False
        for inv in inv_list:
            y = inv.ycor()
            y -= 40
            inv.sety(y)

    if is_fired:
        bomb_y = bomb.ycor()
        bomb_y += bomb_step
        bomb.sety(bomb_y)
        if bomb_y > 300:
            is_fired = False
            bomb.setpos(-320, -320)
            bomb.ht()


turtle.done()

