package CCF;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

public class C210921_3 {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int S = input.nextInt();
        int P = input.nextInt();
        int T = input.nextInt();
        double dt = input.nextDouble();


        Vector<Unit> units = new Vector<>();

        int N_temp = 0;

        while (N_temp < N) {
            int RN = input.nextInt();
            N_temp += RN;
            for (int i = 0; i < RN; i++) {
                units.add(new Unit(
                        input.nextDouble(), input.nextDouble(),
                        input.nextDouble(), input.nextDouble(),
                        input.nextDouble(), input.nextDouble(),
                        dt
                ));
//                System.out.println(units.elementAt(i));
            }
        }

        Vector<Generate> generates = new Vector<>();

        for (int i = 0; i < P; i++) {
            generates.add(new Generate(input.nextInt()));
//            System.out.println(generates.elementAt(i));
        }

        Vector<Connect> connects = new Vector<>();

        for (int i = 0; i < S; i++) {
            connects.add(new Connect(input.nextInt(), input.nextInt(), input.nextDouble(), input.nextInt()));
//            System.out.println(connects.elementAt(i));
        }

        for (int k = 1; k < T; k++) {
            // 遍历突触 对神经元进行传递
            System.out.printf("第%d次传递\n",k);
            for (Connect connect: connects){
                if (connect.trans(k)){
                    System.out.println(connect.s+"突触传递到"+connect.t);
                    units.elementAt(connect.t).get_trans(connect.w);
                }
            }

            // 遍历单元 进行update
            for (int i = 0; i < N; i++) {
                System.out.println("结点"+i+"更新");
                System.out.println(units.elementAt(i));
                units.elementAt(i).update();
                if (units.elementAt(i).is_tran){
                    System.out.println("结点"+i+"发出脉冲");
                    for (Connect connect : connects){
                        if (connect.s == i){
                            connect.trans(k);
                        }
                    }
                    units.elementAt(i).is_tran = false;
                }

            }
            // 遍历脉冲 进行脉冲发生
            for (int i=0;i< P;i++){
                if (generates.elementAt(i).generate()){
                    System.out.println("发生器"+i+"发出脉冲");
                    for (Connect connect : connects){
                        if (connect.s == i+N){
                            connect.add(k);
                        }
                    }
                }
            }

        }


        int max_out = 0;
        int min_out = T+1;

        double max_v = -65536;
        double min_v = 65536;

        for (Unit unit : units){
            if (unit.v > max_v){
                max_v = unit.v;
            }
            if (unit.v < min_v){
                min_v = unit.v;
            }
            if (unit.times > max_out){
                max_out = unit.times;
            }
            if (unit.times < min_out){
                min_out = unit.times;
            }
        }

        System.out.printf("%.3f %.3f\n",min_v,max_v);

        System.out.println(min_out+ " "+ max_out);

    }


}

class Unit {

    public double v, u, a, b, c, d = 0;
    public double dt;
    public double Ik = 0;
    public Boolean is_tran = false;
    public int times = 0;

    Unit(double v, double u, double a, double b, double c, double d, double dt) {
        this.v = v;
        this.u = u;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.dt = dt;
    }

    public void update() {

        double temp_v = this.v;
        double temp_u = this.u;

        this.v = temp_v + dt * (0.04 * Math.pow(temp_v, 2) + 5 * temp_v + 140 - temp_u) + Ik;
        this.u = temp_u + dt * a * (b * temp_v - temp_u);

        Ik = 0;

        if (v >= 30) {
            v = c;
            u += d;
            is_tran = true;
            times += 1;
        }
        is_tran = false;

//        System.out.println(this.v);
    }

    public void get_trans(double w) {
        Ik += w;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "v=" + v +
                ", u=" + u +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", dt=" + dt +
                ", Ik=" + Ik +
                ", is_tran=" + is_tran +
                '}';
    }
}

class Connect {

    int s, t, D;
    double w;

    int times = 0;

    Queue<Integer> next_time = new LinkedList<Integer>();


    Connect(int s, int t, double w, int D) {
        this.s = s;
        this.t = t;
        this.w = w;
        this.D = D;
    }

    void add(int k){
        next_time.offer(k+D);
        times++;
    }

    Boolean trans(int k) {
        if (!next_time.isEmpty()) {
            int next = next_time.element();
            if (next >= k) {
                next_time.poll();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Connect{" +
                "s=" + s +
                ", t=" + t +
                ", D=" + D +
                ", w=" + w +
                ", times=" + times +
                ", next_time=" + next_time +
                '}';
    }
}

class Generate {

    int r;
    static long next = 1;

    Generate(int r) {
        this.r = r;
    }

    static int myrand() {
        next = next * 1103515245 + 12345;
        return (int) ((Long.divideUnsigned(next, 65536)) % 32768);
    }

    Boolean generate() {
        int rand = myrand();
        System.out.println("随机发生"+rand);
        if (rand < r){

            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Generate{" +
                "r=" + r +
                '}';
    }
}