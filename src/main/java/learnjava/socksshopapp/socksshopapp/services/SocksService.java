package learnjava.socksshopapp.socksshopapp.services;

import learnjava.socksshopapp.socksshopapp.model.Color;
import learnjava.socksshopapp.socksshopapp.model.Size;
import learnjava.socksshopapp.socksshopapp.model.Socks;

public interface SocksService {

    //    @PostConstruct
//    private void init() {
//        try {
//            readFromFile();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    Socks addSocks(Socks socks, long quantity);

    Socks editSocks(Socks socks, long quantity);

    long getSocksByParameters(Color color, Size size, int cottonMin, int cottonMax);

    boolean deleteSocks(Socks socks, long quantity);
}
