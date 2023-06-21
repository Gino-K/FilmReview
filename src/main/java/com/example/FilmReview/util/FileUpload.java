package com.example.FilmReview.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

import org.springframework.web.multipart.MultipartFile;

/**
 * Die FileUpload-Klasse enthaelt statische Methoden zum Speichern von Dateien und zum Loeschen von Verzeichnissen.
 */
public class FileUpload {
	
	
    /**
     * Speichert eine hochgeladene Datei unter dem angegebenen Pfad und Dateinamen.
     *
     * @param uploadDir     Das Verzeichnis, in dem die Datei gespeichert werden soll.
     * @param fileName      Der Name der zu speichernden Datei.
     * @param multipartFile Das MultipartFile-Objekt, das die hochgeladene Datei repraesentiert.
     * @throws IOException, falls ein Fehler beim Speichern der Datei auftritt.
     */
	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
	    Path uploadPath = Paths.get(uploadDir);

	    if (!Files.exists(uploadPath)) {
	        Files.createDirectories(uploadPath);
	    }

	    try (InputStream inputStream = multipartFile.getInputStream()) {
	        Path filePath = uploadPath.resolve(fileName);
	        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	    } catch (IOException e) {
	        System.out.println(e);
	    }
	}
	
    /**
     * Loescht das angegebene Verzeichnis und alle darin enthaltenen Dateien und Unterverzeichnisse.
     *
     * @param directoryPath Der Pfad des zu loeschenden Verzeichnisses.
     * @throws IOException, falls ein Fehler beim Loeschen des Verzeichnisses auftritt.
     */
    public static void deleteDirectory(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);

        if (Files.exists(path)) {
            Files.walk(path)
                 .sorted(Comparator.reverseOrder())
                 .map(Path::toFile)
                 .forEach(File::delete);
        }
    }
}
