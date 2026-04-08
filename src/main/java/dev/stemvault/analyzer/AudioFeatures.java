package dev.stemvault.analyzer;

import java.time.Instant;

public record AudioFeatures(
    String filePath, String fileHash, long fileSize,
    int originalSampleRate, int channels, double durationSec,
    Double bpm, String key, String scale,
    String sampleType, String instrumentClass,
    double rmsDb, double peakDb, double spectralCentroid,
    double confidence, Instant analyzedAt
) 
{}


