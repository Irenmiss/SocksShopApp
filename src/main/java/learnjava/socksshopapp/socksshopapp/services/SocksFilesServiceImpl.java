package learnjava.socksshopapp.socksshopapp.services;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SocksFilesServiceImpl implements SocksFilesService {
    @Value("${path.to.files.folder}")
    private String socksFilePath;

    @Value("${name.of.file}")
    private String socksFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(socksFilePath, socksFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(socksFilePath, socksFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getDataFile() {
        return new File(socksFilePath + "/" + socksFileName);
    }

    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(socksFilePath, socksFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean uploadDataFile(MultipartFile file) {
        cleanDataFile();
        File dataFile = getDataFile();
        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
