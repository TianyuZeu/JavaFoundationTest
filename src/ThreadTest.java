

class TickTock{//shared resource
    String state;//ticked tocked

    synchronized void tick(boolean running) throws InterruptedException {
        if(!running){// begin and end
            this.state = "ticked";
            notify();
            return;
        }
        System.out.print("Tick");
        this.state = "ticked";
        notify();//唤醒 tock
        while(!"tocked".equals(state)){
            wait();
        }
    }

    synchronized  void tock(boolean running) throws InterruptedException{
        if(!running){
            this.state = "tocked";
            notify();
            return;
        }
        System.out.println(" Tock");
        this.state = "tocked";
        notify();//唤醒tick
        while(!"ticked".equals(this.state)){
            wait();
        }
    }
}

class MyThread extends Thread{//thread
    String name;
    TickTock tt;

    public MyThread(String name,TickTock tt) {//1.get resources.2.start
        this.name = name;
        this.tt = tt;
        this.start();
    }

    @Override
    public void run() {
        super.run();
        if("Tick".equals(this.name)){//Ticked
            try {
                for(int i = 0;i < 5; i++) tt.tick(true);
                tt.tick(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{//tocked
            try {
                for(int i=0; i<5; i++) tt.tock(true);
                tt.tock(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



public class ThreadTest {//exec

    public static void main(String[] args) {
        TickTock tt = new TickTock();
        MyThread myThread1 = new MyThread("Tick",tt);
        MyThread myThread2 = new MyThread("Tock",tt);

        try{//exec
            myThread1.join();
            myThread2.join();
        }catch(InterruptedException e){//interupting
            e.printStackTrace();
            System.out.println("Main thread interrupted");
        }
    }
}

