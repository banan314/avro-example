package org.example;

import example.avro.User;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;


public class App {

    private static final File file = new File("users.avro");

    public static void main(String[] args) throws IOException {
        User user1 = new User();
        user1.setName("Alyssa");
        user1.setFavoriteNumber(256);
        // Leave favorite color null

        // Alternate constructor
        User user2 = new User("Ben", 7, "red");

        // Construct via builder
        User user3 = User.newBuilder()
                .setName("Charlie")
                .setFavoriteColor("blue")
                .setFavoriteNumber(null)
                .build();

        serializeUsers(user1, user2, user3);
        deserializeUsers();
    }

    private static void deserializeUsers() throws IOException {
        // Deserialize Users from disk
        DatumReader<User> userDatumReader = new SpecificDatumReader<>(User.class);
        try (DataFileReader<User> dataFileReader = new DataFileReader<>(file, userDatumReader)) {
            User user = null;
            while (dataFileReader.hasNext()) {
                // Reuse user object by passing it to next(). This saves us from
                // allocating and garbage collecting many objects for files with
                // many items.
                user = dataFileReader.next(user);
                System.out.println(user);
            }
        }
    }

    private static void serializeUsers(User... users) throws IOException {
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<>(User.class);

        try (DataFileWriter<User> dataFileWriter = new DataFileWriter<>(userDatumWriter)) {
            dataFileWriter.create(users[0].getSchema(), file);

            for (User user : users) {
                // Serialize user
                dataFileWriter.append(user);
            }
        }
    }
}
