package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

import static bg.sofia.uni.fmi.mjt.bookmarks.Response.SAVING_PROBLEM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;

public class FileEditor<T> {
    protected Path path;
    protected Class<T> clazz;

    public FileEditor(final Path path, final Class<T> clazz) {
        this.path = path;
        this.clazz = clazz;
    }

    public void persist(final Map<String, T> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(clazz, SerializationMixIn.class);

        if (!Files.exists(path)) {
            try {
                if (!Files.exists(path.getParent())) {
                    Files.createDirectories(path.getParent());
                }

                Files.createFile(path);
            } catch (IOException e) {
                System.out.println(SAVING_PROBLEM.getMessage());
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path,
                StandardOpenOption.TRUNCATE_EXISTING)) {

            objectMapper.writeValue(writer, map);
        } catch (IOException e) {
            System.out.println(SAVING_PROBLEM.getMessage());
            e.printStackTrace();
        }

    }

    public Map<String, T> load() {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            ObjectMapper objectMapper = new ObjectMapper();

            MapType mapType = objectMapper.getTypeFactory()
                    .constructMapType(LinkedHashMap.class, String.class, clazz);
            Map<String, T> map = objectMapper.readValue(reader, mapType);

            return map;
        } catch (IOException e) {
            return new LinkedHashMap<>();
        }
    }
}
