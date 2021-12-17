package com.factorial.swirl_factorial;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class JuliaFractal {
    // вещественная  и мнимая части постоянной C
    public static double cRe, cIm, zoom;


    //функция зарисовки фрактала
    public static void DrawFractal(int w, int h, GraphicsContext g)
    {
        // при каждой итерации, вычисляется znew = zold² + С

        // вещественная и мнимая части старой и новой
        double newRe, newIm, oldRe, oldIm;
        // Можно увеличивать и изменять положение
        double moveX = 0, moveY = 0;
        //Определяем после какого числа итераций функция должна прекратить свою работу
        int maxIterations = 300;

        //выбираем несколько значений константы С, это определяет форму фрактала         Жюлиа


        //"перебираем" каждый пиксель
        for (int x = 0; x < w; x++)
            for (int y = 0; y < h; y++)
            {
                //вычисляется реальная и мнимая части числа z
                //на основе расположения пикселей,масштабирования и значения позиции
                newRe = 1.5 * (x - w / 2) / (0.5 * zoom * w) + moveX;
                newIm = (y - h / 2) / (0.5 * zoom * h) + moveY;

                //i представляет собой число итераций
                int i;

                //начинается процесс итерации
                for (i = 0; i < maxIterations; i++)
                {

                    //Запоминаем значение предыдущей итерации
                    oldRe = newRe;
                    oldIm = newIm;

                    // в текущей итерации вычисляются действительная и мнимая части
                    newRe = oldRe * oldRe - oldIm * oldIm + cRe;
                    newIm = 2 * oldRe * oldIm + cIm;

                    // если точка находится вне круга с радиусом 2 - прерываемся
                    if ((newRe * newRe + newIm * newIm) > 4) break;
                }

                //определяем цвета
                g.setFill(Color.rgb(255, (i * 9) % 255, 0, (float)((i * 10) % 255) / 255));
                //рисуем пиксель
                g.fillRect(x, y, 1 ,1);
            }
    }
}
