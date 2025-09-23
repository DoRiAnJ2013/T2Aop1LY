// 代码生成时间: 2025-09-24 00:31:44
package com.example.imageresizer;

import io.micronaut.core.io.ResourceLoader;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.MediaType;
import jakarta.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller("/image")
public class ImageResizer {
    @Inject
    private ResourceLoader resourceLoader;
    @Inject
    private ObjectMapper objectMapper;

    private static final String INPUT_DIRECTORY = "resized_images/input";
    private static final String OUTPUT_DIRECTORY = "resized_images/output";
    private static final String TEMP_DIRECTORY = "resized_images/temp";
    private static final int MAX_WIDTH = 800; // Maximum width for resizing
    private static final int MAX_HEIGHT = 600; // Maximum height for resizing

    public ImageResizer() throws IOException {
        // Ensure directories exist
        File inputDir = resourceLoader.get(INPUT_DIRECTORY).orElseThrow(() -> new IOException("Input directory not found")).toFile();
        File outputDir = resourceLoader.get(OUTPUT_DIRECTORY).orElseThrow(() -> new IOException("Output directory not found")).toFile();
        File tempDir = resourceLoader.get(TEMP_DIRECTORY).orElseThrow(() -> new IOException("Temp directory not found")).toFile();
        inputDir.mkdirs();
        outputDir.mkdirs();
        tempDir.mkdirs();
    }

    @Post(value = "/resize", consumes = MediaType.APPLICATION_JSON)
    public String resizeImages(String requestBody) throws IOException {
        // Parse the JSON request body to get the image path and desired dimensions
        ResizeRequest request = objectMapper.readValue(requestBody, ResizeRequest.class);
        String imagePath = request.getImagePath();
        int newWidth = request.getWidth();
        int newHeight = request.getHeight();

        // Validate dimensions
        if (newWidth <= 0 || newHeight <= 0) {
            return objectMapper.writeValueAsString(new ErrorResponse("Invalid dimensions"));
        }

        // Load image and resize
        try {
            File inputFile = new File(imagePath);
            BufferedImage originalImage = ImageIO.read(inputFile);
            double ratio = Math.min((double) newWidth / originalImage.getWidth(), (double) newHeight / originalImage.getHeight());
            int resizedWidth = (int) (ratio * originalImage.getWidth());
            int resizedHeight = (int) (ratio * originalImage.getHeight());
            BufferedImage resizedImage = new BufferedImage(resizedWidth, resizedHeight, originalImage.getType());
            resizedImage.getGraphics().drawImage(originalImage.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

            // Save resized image
            File outputFile = new File(OUTPUT_DIRECTORY + "/" + imagePath);
            ImageIO.write(resizedImage, "jpg", outputFile);

            return objectMapper.writeValueAsString(new SuccessResponse("Image resized successfully", outputFile.getAbsolutePath()));
        } catch (Exception e) {
            return objectMapper.writeValueAsString(new ErrorResponse("Error resizing image: " + e.getMessage()));
        }
    }
}

// Inner class to represent a resize request
class ResizeRequest {
    private String imagePath;
    private int width;
    private int height;

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
}

// Inner class to represent a successful response
class SuccessResponse {
    private String message;
    private String outputPath;

    public SuccessResponse(String message, String outputPath) {
        this.message = message;
        this.outputPath = outputPath;
    }

    public String getMessage() { return message; }
    public String getOutputPath() { return outputPath; }
}

// Inner class to represent an error response
class ErrorResponse {
    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() { return error; }
}