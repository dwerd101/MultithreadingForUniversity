package com.dwerd.tasks.easy.task2;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class MyThread {



    public static   void main(String[] args) {

        Semaphore forkRight = new Semaphore(1);
        Semaphore forkLeft = new Semaphore(1);
        ForkA forkA = new ForkA();
        ForkB forkB = new ForkB();
        forkA.setForkB(forkB);
        forkB.setForkA(forkA);
       Person person1 = new Person(forkLeft,forkRight);
        Person person2 = new Person(forkLeft,forkRight);
        Person person3 = new Person(forkLeft,forkRight);
        Person person4 = new Person(forkLeft,forkRight);
        Person person5 = new Person(forkLeft,forkRight);
        person1.setForkA(forkA);
        person1.setForkB(forkB);
        person2.setForkA(forkA);
        person2.setForkB(forkB);
        person3.setForkA(forkA);
        person3.setForkB(forkB);
        person3.setForkA(forkA);
        person3.setForkB(forkB);
        person4.setForkA(forkA);
        person4.setForkB(forkB);
        person5.setForkA(forkA);
        person5.setForkB(forkB);

        person1.start();
        person2.start();
        person3.start();
        person4.start();
        person5.start();


    }

    static class Person extends Thread{
        int gramPerson = 100;
        ForkB forkB;
        ForkA forkA;

        public void setForkB(ForkB forkB) {
            this.forkB = forkB;
        }

        public void setForkA(ForkA forkA) {
            this.forkA = forkA;
        }

        Semaphore forkRight;
        Semaphore forkLeft;

        public int getGramPerson() {
            return gramPerson;
        }

        public void setGramPerson(int gramPerson) {
            this.gramPerson = gramPerson;
        }

        public void minusGramPerson(int a) {
            setGramPerson(gramPerson-a);
        }

        public Person(Semaphore forkLeft, Semaphore forkRight) {
            this.forkLeft = forkLeft;
            this.forkRight = forkRight;


        }



        @SneakyThrows
        @Override
        public void run() {

          /*  while (true) {
                forkA.setPerson(this);
                forkB.setPerson(this);
                System.out.println(Thread.currentThread().getName()+" жду");
                forkRight.acquire();
                if(getGramPerson()>0) {
                    forkA.eat();
                    System.out.println(this.getGramPerson()+" "+Thread.currentThread().getName());
                    forkRight.release();
                    forkLeft.acquire();
                    if(this.getGramPerson()>0) {
                        forkB.eat();
                        System.out.println(this.getGramPerson()+" "+Thread.currentThread().getName());
                    }
                    forkLeft.release();
                }
                if(this.getGramPerson()<=0) {
                    break;
                }
            }
            System.out.println(Thread.currentThread().getName()+" Закончил есть");
        }*/




            while (true) {
                forkA.setPerson(this);
                forkB.setPerson(this);
              System.out.println(Thread.currentThread().getName()+" жду");
              synchronized (forkA) {
                  if(getGramPerson()>0) {
                      forkA.eat();
                      System.out.println(this.getGramPerson()+" "+Thread.currentThread().getName());
                  }
                  synchronized (forkB) {
                      if(this.getGramPerson()>0) {
                          forkB.eat();
                          System.out.println(this.getGramPerson()+" "+Thread.currentThread().getName());
                      }
                  }
              }
              if(this.getGramPerson()<=0) {
                  break;
              }
          }
            System.out.println(Thread.currentThread().getName()+" Закончил есть");
        }
    }

   static class Spaghetti {
        //кол-во спаггетти
        List<Integer> gram = new LinkedList<>();
        public Spaghetti() {
            for (int i = 1; i <= 100; i++) {
                gram.add(i);
            }
        }

        public List<Integer> getGram() {
            return gram;
        }
    }

   static class ForkA {
        ForkB forkB;

       public void setForkB(ForkB forkB) {
           this.forkB = forkB;
       }

       public ForkA() {
       }

       public void setPerson(Person person) {
           this.person = person;
       }

       Person person;
        public ForkA(Person person) {
            this.person = person;

        }

        public synchronized   void eat() throws InterruptedException {
            System.out.println(Thread.currentThread().getName()+" кушаю");
           forkB.eatNow(person);
        }
        public synchronized    void eatNow(Person person) throws InterruptedException {
            int eat = randomNumber();
            person.minusGramPerson(eat);

         //   person.getGramPerson().removeIf(e -> e >= eat);
       /*     for (int i = 1; i <= person.getGramPerson().size(); i++) {
                if (count <= eat && person.getGramPerson().size() > 0) {
                    person.getGramPerson().remove(0);
                    count++;
                }
            }
            for (int i = 1; i <= person.getGramPerson().size(); i++) {
                if (count <= eat && person.getGramPerson().size() > 0) {
                    person.getGramPerson().remove(0);
                    count++;
                }
            }*/
                    System.out.println(Thread.currentThread().getName()+" поел, теперь думаю");




        }
    }
  static class ForkB {

        ForkA forkA;

      public void setForkA(ForkA forkA) {
          this.forkA = forkA;
      }

      public ForkB() {
      }

      public void setPerson(Person person) {
          this.person = person;
      }


        Person person;

        public ForkB(Person person) {
            this.person = person;

        }

        public synchronized void eat() throws InterruptedException {
            System.out.println(Thread.currentThread().getName()+" кушаю");
            forkA.eatNow(person);
        }

        public synchronized    void eatNow(Person person) throws InterruptedException {
            int eat = randomNumber();
            person.minusGramPerson(eat);

            /*for (int i = 1; i <= person.getGramPerson().size(); i++) {
                if (count <= eat && person.getGramPerson().size() > 0) {
                    person.getGramPerson().remove(0);
                    count++;
                }
            }
            for (int i = 1; i <= person.getGramPerson().size(); i++) {
                if (count <= eat && person.getGramPerson().size() > 0) {
                    person.getGramPerson().remove(0);
                    count++;
                }
            }*/
                System.out.println(Thread.currentThread().getName()+" поел, теперь думаю");


        }
    }

   static private int randomNumber() {
        final int min = 1; // Минимальное число для диапазона
        final int max = 100; // Максимальное число для диапазона

        class CalculateRandom{
            public  int rnd(int min, int max)
            {
                max -= min;
                return (int) (Math.random() * ++max) + min;
            }
        }
        final int rnd = new CalculateRandom().rnd(min,max);
        return rnd;


    }





















   /* public static void main(String[] args) {
        Semaphore forks = new Semaphore(2);

        new Person(forks).start();
        new Person(forks).start();
        new Person(forks).start();
        new Person(forks).start();
        new Person(forks).start();



    }

    static class Person extends Thread {
        Semaphore forks;

        public Person(Semaphore forks) {
            this.forks = forks;
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(this.getName()+"Жду");
            forks.acquire();
            System.out.println(this.getName()+"Кушаю");
            Thread.sleep(1000);
            System.out.println(this.getName()+"Покушал");
            forks.release();
        }
    }*/
}
