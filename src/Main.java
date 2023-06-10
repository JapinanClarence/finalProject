
import java.util.Arrays;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author japin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //System.out.println(Peripherals.sortNameAsc(Peripherals.fetchInputDevices()));
        System.out.println(Arrays.deepToString(Peripherals.peripherals()));
        new PeripheralsFrame().setVisible(true);
    }

}
