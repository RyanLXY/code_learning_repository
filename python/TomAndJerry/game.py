import turtle as t
import random
import time

# 记录时间
start_time = time.time()
used_time = 0

# 创建背景
is_start = True
game = t.Screen()
game.setup(840, 700)
game.title('Tom and Jerry')
game.bgpic('bg.gif')
t.tracer(3)  # 3次循坏刷新屏幕

# 创建猫咪
cat = t.Turtle()
cat.color('red')
cat.shapesize(2, 2)
cat.speed(0)
# t.addshape('cat.gif')
# cat = t.Turtle()
# cat.shape('cat.gif')
cat.up()
cat_speed = 1
rat_speed = 1

# 添加计时
pen = t.Turtle()
pen.speed(0)
pen.ht()
pen.up()
pen.setpos(-410, 320)
pen.color('black')
pen.write("Time: ", align='left', font=('Arial', 20, 'bold'))

pen2 = t.Turtle()
pen2.speed(0)
pen2.ht()
pen2.up()
pen2.setpos(-350, 320)
pen2.color('black')


def update_time():
    # rand = random.randint(0, 100)
    global used_time
    now_used_time = int(time.time() - start_time)

    if now_used_time > used_time:
        used_time = now_used_time
        time_str = str(used_time)
        pen2.clear()
        pen2.write(time_str, align='left', font=('Arial', 20, 'bold'))


# 创建老鼠
rat_num = 1
rats = []
t.register_shape('rat.gif')
# t.addshape('rat.gif')

for r in range(rat_num):
    rat = t.Turtle()
    rat.ht()
    rat.up()
    rat.speed(0)
    rat.left(random.randint(0, 360))
    rat.shape('rat.gif')
    x = random.randint(-400, 400)
    y = random.randint(-300, 300)
    rat.setpos(x, y)
    rat.st()
    rats.append(rat)


def move_left():
    cat.left(30)


def move_right():
    cat.right(30)


def speed_up():
    global cat_speed
    cat_speed += 1


def slowdown():
    global cat_speed
    if cat_speed > 1:
        cat_speed -= 1
    else:
        cat_speed = cat_speed


def catch(check_rat):
    if cat.distance(check_rat) < 10:
        check_rat.ht()
        rats.remove(check_rat)


pen3 = t.Turtle()
pen3.ht()


def game_over():
    pen3.ht()
    pen3.speed(0)
    pen3.up()
    pen3.color('red')
    over_str = " Game Over! Use " + str(used_time) + " s\n Press space to restart!"
    pen3.write(over_str, align='center', font=('Arial', 30, 'bold'))
    pen3.st()


def start_game():
    global start_time
    start_time = time.time()
    pen2.clear()
    pen2.write("0", align='left', font=('Arial', 20, 'bold'))
    global is_start
    while is_start:
        update_time()
        cat.fd(cat_speed)
        cat_x = cat.xcor()
        cat_y = cat.ycor()
        if cat_x > 410 or cat_x < -410 or cat_y > 310 or cat_y < -310:
            cat.right(180)

        # 老鼠移动
        # rat.fd(rat_speed)
        for r1 in rats:
            r1.fd(rat_speed)
            catch(r1)
            r1_x = r1.xcor()
            r1_y = r1.ycor()
            if r1_x > 410 or r1_x < -410 or r1_y > 310 or r1_y < -310:
                r1.right(180)

        # 判断游戏结束
        if len(rats) == 0:
            game_over()
            is_start = False


def restart():
    global is_start
    global used_time
    global cat_speed
    cat_speed = 1
    if not is_start:
        used_time = 0
        for r1 in range(rat_num):
            rat_new = t.Turtle()
            rat_new.ht()
            rat_new.up()
            rat_new.speed(0)
            rat_new.left(random.randint(0, 360))
            rat_new.shape('rat.gif')
            x = random.randint(-400, 400)
            y = random.randint(-300, 300)
            rat_new.setpos(x, y)
            rat_new.st()
            rats.append(rat_new)

        is_start = True
        pen3.clear()
        pen3.ht()
        start_game()


t.listen()
t.onkey(move_left, 'Left')
t.onkey(move_right, 'Right')
t.onkey(speed_up, 'Up')
t.onkey(slowdown, 'Down')
t.onkey(restart, 'space')
start_game()


game.mainloop()
