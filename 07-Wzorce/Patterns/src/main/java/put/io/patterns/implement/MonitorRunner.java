package put.io.patterns.implement;

public class MonitorRunner {

    public static void main(String args[]) {
        // tworzymy monitor
        SystemMonitor monitor = new SystemMonitor();

        // tworzymy obserwatora i dodajemy do monitora
        SystemStateObserver infObserver = new SystemInfoObserver();
        SystemStateObserver garbageObserver = new SystemGarbageCollectorObserver();
        SystemStateObserver coolerObserver = new SystemCoolerObserver();
        SystemStateObserver usbObserver = new USBDeviceObserver();
        monitor.addSystemStateObserver(infObserver);
        monitor.addSystemStateObserver(coolerObserver);
        monitor.addSystemStateObserver(garbageObserver);
        monitor.addSystemStateObserver(usbObserver);

        while (true) {
            monitor.probe();
            //monitor.removeSystemStateObserver(infObserver);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
