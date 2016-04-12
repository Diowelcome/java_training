package ru.stesting.jtraining.sandbox;

public class HelloWorld {

  public static void main(String[] args) {
  //  hello("world");
  //
  //  Задание 2
  //
    Point p1 = new Point(-4, -3);
    Point p2 = new Point( 4,  3);
    System.out.println("Расстояние между точками P1(" + p1.x + ", " + p1.y + ") и P2(" + p2.x + ", " + p2.y + ") = " + distance(p1, p2));

    System.out.println("Расстояние между точками P1(" + p1.x + ", " + p1.y + ") и P2(" + p2.x + ", " + p2.y + ") = " + p1.distance(p2));
    System.out.println("Расстояние между точками P1(" + p1.x + ", " + p1.y + ") и P2(" + p2.x + ", " + p2.y + ") = " + p2.distance(p1));


  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
  }

}