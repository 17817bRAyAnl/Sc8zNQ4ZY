// 代码生成时间: 2025-08-25 22:23:12
@Controller
@RestControllerAdvice
@RequestMapping("/api/images")
public class ImageResizeBatchController {

    @Autowired
    private ImageService imageService;

    private static final Logger logger = LoggerFactory.getLogger(ImageResizeBatchController.class);

    @PostMapping("/resize")
    public ResponseEntity<?> resizeImages(@RequestParam("images") MultipartFile[] imageFiles, @RequestParam("targetSize") int targetSize) {
        try {
            List<ResizeResult> results = imageService.resizeImages(Arrays.asList(imageFiles), targetSize);
            return ResponseEntity.ok(results);
        } catch (Exception ex) {
            logger.error("Failed to resize images", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error resizing images");
        }
    }
}

/**
 * Image Service
 * Handles image resizing logic.
 */
@Service
public class ImageService {

    public List<ResizeResult> resizeImages(List<MultipartFile> imageFiles, int targetSize) {
        List<ResizeResult> results = new ArrayList<>();
        for (MultipartFile file : imageFiles) {
            try {
                Image resizedImage = resizeImage(file.getBytes(), targetSize);
                ResizeResult result = new ResizeResult(resizedImage, file.getOriginalFilename());
                results.add(result);
            } catch (IOException ex) {
                results.add(new ResizeResult(null, file.getOriginalFilename(), ex.getMessage()));
            }
        }
        return results;
    }

    private Image resizeImage(byte[] imageBytes, int targetSize) throws IOException {
        // Image resizing logic here
        // This is a placeholder for the actual image resizing code
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
        BufferedImage resizedImage = new BufferedImage(targetSize, targetSize, originalImage.getType());
        // ... resize logic ...
        return resizedImage;
    }
}

/**
 * Resize Result
 * Holds the result of an image resize operation.
 */
public class ResizeResult {
    private Image resizedImage;
    private String originalFilename;
    private String errorMessage;

    public ResizeResult(Image resizedImage, String originalFilename) {
        this.resizedImage = resizedImage;
        this.originalFilename = originalFilename;
    }

    public ResizeResult(Image resizedImage, String originalFilename, String errorMessage) {
        this.resizedImage = resizedImage;
        this.originalFilename = originalFilename;
        this.errorMessage = errorMessage;
    }

    // Getters and setters
}

/**
 * Global Exception Handler
 * Handles exceptions across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex, WebRequest request) {
        String error = "An error occurred: " + ex.getMessage();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
