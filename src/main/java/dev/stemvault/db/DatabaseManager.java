package dev.stemvault.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String TABLE_SAMPLES = "CREATE TABLE IF NOT EXISTS samples (id, file_path, file_hash, file_size, original_sample_rate,channels, duration_sec, bpm, key, scale,sample_type, instrument_class, rms_db, peak_db, spectral_centroid,confidence, analyzed_at)";
    private static final String TABLE_SCAN_HISTORY = "CREATE TABLE IF NOT EXISTS scan_history (id, folder_path, scanned_at, file_count)";
    private static final String PRAGMA_WAL_MODE = "PRAGMA journal_mode =WAL";


    private static final String USER_HOME = System.getProperty("user.home");
    private static final String STEMVAULT_DB =
        USER_HOME + "/.stemvault/catalog.db";

    public Connection connect() throws SQLException, IOException {
        Path path = Path.of(STEMVAULT_DB);
        Files.createDirectories(path.getParent());
        return DriverManager.getConnection("jdbc:sqlite:" + STEMVAULT_DB);
    }
    private void initialize(Connection connect) throws SQLException {
        try (Statement statement = connect.createStatement()) {
            statement.execute(PRAGMA_WAL_MODE);
            statement.execute(TABLE_SAMPLES);
            statement.execute(TABLE_SCAN_HISTORY);
        }
        
    }

    public Connection getConnection() throws SQLException, IOException {
        Connection connection = connect();
        initialize(connection);
        return connection;
    }

}
