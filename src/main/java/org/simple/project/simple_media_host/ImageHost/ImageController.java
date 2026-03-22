package org.simple.project.simple_media_host.ImageHost;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageRepo repo;

    public ImageController(ImageRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        ImageEntity image = repo.findById(id).orElseThrow();

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(image.getContentType()))
                .body(image.getData());
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file")MultipartFile file) throws IOException {
        ImageEntity image = new ImageEntity();

        image.setData(file.getBytes());
        image.setContentType(file.getContentType());

        repo.save(image);

        return "Image uploaded with ID: " + image.getId();
    }
}
