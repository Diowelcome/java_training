package ru.stesting.jtraining.sandbox;

public class HelloWorld {

  public static void main(String[] args) {
    hello("world");
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
  }

}