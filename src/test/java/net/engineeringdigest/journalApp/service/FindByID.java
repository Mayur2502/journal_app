package net.engineeringdigest.journalApp.service;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;


public class FindByID implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(new ObjectId("67b437cccc84d6781d652c4c")),
                Arguments.of(new ObjectId("66b437cccc84d6781d652c4c"))
        );
    }
}
