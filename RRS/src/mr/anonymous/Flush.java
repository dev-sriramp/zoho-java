package mr.anonymous;

public class Flush {
    static void flush() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}