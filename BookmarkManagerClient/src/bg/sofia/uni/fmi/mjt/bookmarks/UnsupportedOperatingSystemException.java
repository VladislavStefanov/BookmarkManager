package bg.sofia.uni.fmi.mjt.bookmarks;

public class UnsupportedOperatingSystemException extends Exception {

    private static final String OPERATING_SYSTEM_NOT_SUPPORTED = "This operating system is not supported.";
    private static final long serialVersionUID = 3718679139925991513L;

    public UnsupportedOperatingSystemException() {
        super(OPERATING_SYSTEM_NOT_SUPPORTED);
    }
}
