package dha.lockrepo.view.txt;


public class MainEntry {

    public static void main(String[] args) {
        System.out
                .println("\n       Welcome to LockRepo 1.0\n"
                        + "            @author zyzo\n"
                        + "            For any suggestions or bug reports, please send an email to danghaian168@gmail.com. Thanks!\n"
                        + "Your wish ?");
        TxtController ctrl = new TxtController();
        ctrl.run();
    }
}
