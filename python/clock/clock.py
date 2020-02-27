import turtle
import datetime
import time

clock = turtle.Screen()
clock.bgcolor('black')
clock.setup(600, 600)
clock.tracer(0)

pen = turtle.Turtle()
pen.ht()
pen.speed(0)


def draw_clock(h, m, s):
    # 表盘
    pen.pensize(5)
    pen.clear()
    pen.up()
    pen.color('white')
    pen.goto(0, -210)
    pen.down()
    pen.seth(0)
    pen.circle(210)

    # 刻度
    pen.up()
    pen.goto(0, 0)
    pen.seth(90)

    # for _ in range(12):    _ 默认为不使用变量
    for i in range(60):
        pen.fd(190)
        pen.down()
        if i % 5 == 0:
            pen.fd(20)
        else:
            pen.up()
            pen.fd(10)
            pen.down()
            pen.fd(8)
        pen.up()
        pen.goto(0, 0)
        pen.rt(6)

    # 时针
    pen.up()
    pen.goto(0, 0)
    pen.down()
    pen.pensize(8)
    pen.color('blue')
    pen.seth(90)
    # pen.right(h/12 *360)
    pen.right((h + m / 60 + s / 3600) / 12 * 360)
    pen.fd(80)

    # 分针
    pen.up()
    pen.goto(0, 0)
    pen.down()
    pen.pensize(5)
    pen.color('green')
    pen.seth(90)
    # pen.right(m / 60 * 360)
    pen.right((m + s / 60) / 60 * 360)
    pen.fd(120)

    # 秒针
    pen.up()
    pen.goto(0, 0)
    pen.down()
    pen.pensize(2)
    pen.color('red')
    pen.seth(90)
    pen.right(s / 60 * 360)
    pen.fd(180)

    pen.up()
    pen.goto(0, 250)
    pen.color('white')
    font = ('Arial', 20, 'bold')
    str = 'Hello World! Today is {}/{}/{}'.format(now.year,now.month,now.day)
    pen.write(str, align='center', font=font)


def draw_dot():
    pen2 = turtle.Turtle()
    pen2.ht()
    pen2.speed(0)
    pen2.color('white')
    pen2.pensize(5)
    pen2.up()
    pen2.goto(0, -1)
    pen2.down()
    pen2.circle(2)


while True:
    clock.update()
    time.sleep(1)
    now = datetime.datetime.now()
    draw_clock(now.hour, now.minute, now.second)
    draw_dot()

clock.mainloop()
