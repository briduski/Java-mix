package org.example.webs.baeldung.trywithresources;

// https://www.baeldung.com/java-try-with-resources

import java.util.Random;

public class CustomAutocloseable implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("Closed CustomAutocloseable");
        Random random = new Random();
        int res = random.nextInt(10);
        if (res % 2 == 0) {
            throw new Exception("Exception closing CustomAutocloseable ... "+res);
        }
    }
    public void printSth() {
        System.out.println("This an example of custom autocloseable interface :)");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            try (CustomAutocloseable cAutocloseable = new CustomAutocloseable()) {
                cAutocloseable.printSth();
            } catch (Exception e) {
                System.out.println("# " + i+ "  Oh mama! " + e.getMessage());
            }
        }
    }
}
