package com.anandapp.aiollama.model;

public record Answer(String answer) {
}

/* [Notes]  key points regarding "Records"

## concept
- introduced in Java 14
- primary purpose is to hold data,
- record classes are a compact syntax for creating immutable data carriers.
- Compact Constructor: The record defines a constructor that automatically accepts the field values. You don’t need to write a constructor, getter methods, or other boilerplate code like toString, equals, and hashCode methods.

## Automatic Methods: For each field in the record, Java automatically generates:
- A getter method with the same name as the field (answer() in this case).
- toString() method: This method will return a string representation of the object in the format Answer[answer=VALUE].
- equals() and hashCode() methods: These are useful for comparing records and for using them in hash-based collections like HashSet or HashMap.
 */