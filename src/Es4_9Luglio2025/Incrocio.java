package Es4_9Luglio2025;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class Incrocio extends UnicastRemoteObject implements ImpiantoSemaforico {
    private boolean semPrinc = true;  // inizialmente verde per principale
    private boolean semSec = false;   // inizialmente rosso per secondaria
    private int contSecondaria = 0;   // conta auto passate dalla secondaria
    private boolean cambioInCorso = false;

    private LinkedList<Integer> codaPrincipale = new LinkedList<>();
    private LinkedList<Integer> codaSecondaria = new LinkedList<>();

    protected Incrocio() throws RemoteException {
    }

    @Override
    public synchronized void arrivoPrincipale(int id) throws InterruptedException {
        codaPrincipale.add(id);
        System.out.println("Auto " + id + " arriva sulla principale");

        // Aspetta se il semaforo è rosso
        while (!semPrinc) {
            wait();
        }

        System.out.println("Auto " + id + " passa dalla principale");
    }

    @Override
    public synchronized void arrivoSecondario(int id) throws InterruptedException {
        codaSecondaria.add(id);
        System.out.println("Auto " + id + " arriva sulla secondaria");

        // Se è la prima auto in attesa e il semaforo è rosso, avvia il cambio
        if (codaSecondaria.size() == 1 && !semSec && !cambioInCorso) {
            cambioInCorso = true;
            // Simula il minuto di attesa (usa 1000ms per test, dovrebbe essere 60000)
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    synchronized (Incrocio.this) {
                        semPrinc = false;
                        semSec = true;
                        contSecondaria = 0;
                        cambioInCorso = false;
                        notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        // Aspetta finché:
        // 1. Il semaforo è rosso, OPPURE
        // 2. Sono già passate 3 auto in questo ciclo
        while (!semSec || contSecondaria >= 3) {
            wait();
        }

        contSecondaria++;
        System.out.println("Auto " + id + " passa dalla secondaria (n. " + contSecondaria + ")");
    }

    @Override
    public synchronized void rilascioIncrocio(int id) {
        if (codaPrincipale.contains(id)) {
            codaPrincipale.remove(Integer.valueOf(id));
            System.out.println("Auto " + id + " ha lasciato l'incrocio (principale)");
        } else if (codaSecondaria.contains(id)) {
            codaSecondaria.remove(Integer.valueOf(id));
            System.out.println("Auto " + id + " ha lasciato l'incrocio (secondaria)");

            // Se erano le ultime 3 auto, cambia semaforo
            if (contSecondaria == 3) {
                semSec = false;
                semPrinc = true;
                contSecondaria = 0;
                System.out.println("=== SEMAFORO: Verde per principale, Rosso per secondaria ===");
            }
        }

        notifyAll();
    }
}