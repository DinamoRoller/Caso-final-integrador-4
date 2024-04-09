import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

public class raton {

    public static void main(String[] args) {
        while (true) {
            // Obtener la posición actual del ratón
            PointerInfo pointerInfo = MouseInfo.getPointerInfo();
            Point point = pointerInfo.getLocation();

            // Mostrar la posición del ratón en la consola
            System.out.println("Posición del ratón: (" + point.x + ", " + point.y + ")");

            // Esperar un breve tiempo antes de actualizar la posición del ratón nuevamente
            try {
                Thread.sleep(100); // Actualiza la posición del ratón cada 100 milisegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
