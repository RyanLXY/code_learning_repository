import turtle
import random

game = turtle.Screen()  # 默认 竖屏3/4 横屏1/2
game.setup(900, 700)
game.bgpic('bg.gif')
game.tracer(0)

boy_speed = 3
score = 0
life = 1

pen = turtle.Turtle()
pen.ht()
pen.speed(0)
pen.up()
pen.goto(-400, 320)
score_text = "Score: {}  Life: {}".format(score, life)
font = ('Arial', 20, 'bold')
pen.write(score_text, align='left', font=font)


def to_left():
    global fx
    global boy_speed
    if fx == 'L':
        boy_speed += 1
    else:
        boy_speed = 3
        fx = 'L'


def to_right():
    global fx
    global boy_speed
    if fx == 'R':
        boy_speed += 1
    else:
        boy_speed = 3
        fx = 'R'


def build_objects(shape, number):
    gift_list = []
    for _ in range(number):
        gift = turtle.Turtle()
        gift.ht()
        gift.speed(0)
        gift.up()
        gift.shape(shape)
        x = random.randint(-400, 400)
        y = random.randint(0, 300)
        gift.goto(x, y)
        gift.fail_speed = random.randint(1, 5)
        gift.st()
        gift_list.append(gift)
    return gift_list


def fail_gifts(gifts, add_score):
    global score
    for g in gifts:
        g.sety(g.ycor() - g.fail_speed)
        if g.ycor() < -350:
            g.sety(300)
        if g.distance(boy) < 40:
            g.sety(300)
            score = score + add_score
            pen.clear()
            pen.write("Score: {}  Life: {}".format(score, life), align='left', font=font)


turtle.register_shape('boy.gif')
turtle.register_shape('gift1.gif')
turtle.register_shape('gift2.gif')
turtle.register_shape('gift3.gif')
turtle.register_shape('boom.gif')

boy = turtle.Turtle()
boy.ht()
boy.speed(0)
boy.up()
boy.shape('boy.gif')
boy.goto(0, -250)
boy.st()
fx = 'R'

gift1_number = 5
gift1_list = build_objects('gift1.gif', gift1_number)
gift2_number = 5
gift2_list = build_objects('gift2.gif', gift2_number)
gift3_number = 5
gift3_list = build_objects('gift3.gif', gift3_number)
boom_number = 5
boom_list = build_objects('boom.gif', boom_number)


turtle.listen()
turtle.onkey(to_left, 'Left')
turtle.onkey(to_right, 'Right')

game_over = False

while True:
    if game_over:
        break
    game.update()
    if fx == 'R':
        x = boy.xcor()
        x = x + boy_speed
        if x > 450:
            to_left()
        else:
            boy.setx(x)
    elif fx == 'L':
        x = boy.xcor()
        x = x - boy_speed
        if x < -450:
            to_right()
        else:
            boy.setx(x)
        # boy.setx(boy.xcor() - boy_speed)

    fail_gifts(gift1_list, 3)
    fail_gifts(gift2_list, 5)
    fail_gifts(gift3_list, 10)

    for b in boom_list:
        b.sety(b.ycor() - b.fail_speed)
        if b.ycor() < -350:
            b.sety(300)
        if b.distance(boy) < 40:
            b.sety(300)
            life = life - 1
            pen.clear()
            pen.write("Score: {}  Life: {}".format(score, life), align='left', font=font)
            if life == 0:
                game_over = True
                pen2 = turtle.Turtle()
                pen2.ht()
                pen2.speed(0)
                pen2.up()
                pen2.goto(-100, 0)
                pen2.color('red')
                score_text = "GAME OVER"
                font = ('Arial', 30, 'bold')
                pen2.write(score_text, align='left', font=font)

game.mainloop()
