package bg.sofia.uni.fmi.mjt.bookmarks.repositories.files;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface SerializationMixIn {
    @JsonIgnore
    boolean isEmpty();
}
