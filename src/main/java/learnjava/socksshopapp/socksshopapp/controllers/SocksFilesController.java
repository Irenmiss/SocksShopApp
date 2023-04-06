package learnjava.socksshopapp.socksshopapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import learnjava.socksshopapp.socksshopapp.services.SocksFilesService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


@RestController
@RequestMapping("/socks/files")
@Tag(name = "Файлы", description = "Работа с файлами, содержащими информацию о товаре")
public class SocksFilesController {
    private final SocksFilesService socksFilesService;

    public SocksFilesController(SocksFilesService socksFilesService) {
        this.socksFilesService = socksFilesService;
    }

    @GetMapping("/export")
    @Operation(
            summary = "Загрузка файла",
            description = "Выгрузка данных о товаре на складе в виде файла"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл успешно сформирован и загружен"),
            @ApiResponse(
                    responseCode = "204",
                    description = "Файл отсутствует")
    })
    public ResponseEntity<InputStreamResource> downloadFile() throws FileNotFoundException {
        File file = socksFilesService.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Socks.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Загрузка нового файла с товаром",
            description = "Загрузка данных о товаре в виде json-файла с заменой существующего"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл успешно загружен"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка сервера"
            )
    })
    public ResponseEntity<Void> uploadDataFIle(@RequestParam MultipartFile file) {
        socksFilesService.uploadDataFile(file);
        if (file.getResource().exists()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
