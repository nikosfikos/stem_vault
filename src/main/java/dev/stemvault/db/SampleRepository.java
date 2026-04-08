package dev.stemvault.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.stemvault.analyzer.AudioFeatures;

public class SampleRepository {

    public void saveSample(AudioFeatures features){
        try (Connection connection = new DatabaseManager().getConnection()) {
                String sql = "INSERT INTO samples (file_path, file_hash, file_size, original_sample_rate, channels, duration_sec, bpm, key, scale, sample_type, instrument_class, rms_db, peak_db, spectral_centroid, confidence, analyzed_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, features.filePath());
                    statement.setString(2, features.fileHash());
                    statement.setLong(3, features.fileSize());
                    statement.setInt(4, features.originalSampleRate());
                    statement.setInt(5, features.channels());
                    statement.setDouble(6, features.durationSec());
                    statement.setDouble(7, features.bpm());
                    statement.setString(8, features.key());
                    statement.setString(9, features.scale());
                    statement.setString(10, features.sampleType());
                    statement.setString(11, features.instrumentClass());
                    statement.setDouble(12, features.rmsDb());
                    statement.setDouble(13, features.peakDb());
                    statement.setDouble(14, features.spectralCentroid());
                    statement.setDouble(15, features.confidence());
                    statement.setTimestamp(16, java.sql.Timestamp.from(features.analyzedAt()));
                    statement.executeUpdate();
                }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public String findDuplicates(String fileHash){
        try (Connection connection = new DatabaseManager().getConnection()) {
            String sql = "SELECT file_path FROM samples WHERE file_hash = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, fileHash);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getString("file_path");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();

    } return null;
    }



}
