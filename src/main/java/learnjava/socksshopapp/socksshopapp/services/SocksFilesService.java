package learnjava.socksshopapp.socksshopapp.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface SocksFilesService {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    boolean cleanDataFile();

    boolean uploadDataFile(MultipartFile file);
}
