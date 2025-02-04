package gov.milove.controllers.abstr;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Tag(name = "Download")
@RequestMapping("/api")
public interface IUpload {

    @Operation(summary = "Get image by id")
    @GetMapping("/download/image/{id}")
    ResponseEntity<byte[]> getImage(@PathVariable String id);

    @Operation(summary = "Get image by id")
    @GetMapping("/download/v2/image/{id}")
    ResponseEntity<byte[]> getImageV2(@PathVariable String id, HttpServletResponse response);

    @Operation(summary = "Get document by filename")
    @GetMapping("/download/file/{fileName}")
    byte[] findDocumentByFilename(@PathVariable String fileName, HttpServletResponse response) throws UnsupportedEncodingException;
}
