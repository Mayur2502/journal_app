package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class FindByUsername implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of("mayur"),
                Arguments.of("ram"),
                Arguments.of("Danny")
//                Arguments.of(User.builder().userName("ghansham").password("hello").build()),
//                Arguments.of(User.builder().userName("hema").password("").build())
                );
    }
}
