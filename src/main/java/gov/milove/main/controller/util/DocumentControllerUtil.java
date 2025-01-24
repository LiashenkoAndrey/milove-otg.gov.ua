package gov.milove.main.controller.util;

import gov.milove.main.exception.DocumentCrudServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/document")
public class DocumentControllerUtil {




    @PostMapping("/update")
    public ResponseEntity<String> updateDocument(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam(name = "title",required = false) String title,
            @RequestParam("filename") String filename) {

        try {
//            documentService.updateDocument(filename, file, title);
            return ControllerUtil.ok("Оновлення успішне");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ControllerUtil.error("Виникли проблеми з видаленням");
        }
    }


    @GetMapping("/delete")
    public ResponseEntity<String> deleteDocument(@RequestParam("filename") String filename) {

        try {
//            documentService.deleteDocumentByFilename(filename);
            return ControllerUtil.ok("Файл успішно видалений");
        } catch (DocumentCrudServiceException ex) {
            ex.printStackTrace();
            return ControllerUtil.error("Виникли проблеми з видаленням файлу");
        }
    }
}
