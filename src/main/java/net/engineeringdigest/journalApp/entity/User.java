package net.engineeringdigest.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
public class User {
    // user login functionality, username, password, its associated journal entry
    @Id
    private ObjectId id;

    @Indexed(unique = true) // every username is unique, it has to be set in properties
    @NonNull
    private String username;

    @NonNull
    private String password;

    @DBRef // links the two collections users and journal_entries
    private List<JournalEntry> journalEntries = new ArrayList<>(); // initially empty list
    private List<String> roles;
}
