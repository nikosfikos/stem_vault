package dev.stemvault.scanner;

public enum AudioExtension {
    MP3(".mp3"),
    WAV(".wav"),
    FLAC(".flac"),
    OGG(".ogg"),
    JAVA(".java");

    private final String extension;

    AudioExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
