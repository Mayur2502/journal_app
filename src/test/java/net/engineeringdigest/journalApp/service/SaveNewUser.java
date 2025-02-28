package net.engineeringdigest.journalApp.service;

import net.bytebuddy.implementation.MethodCall;
import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class SaveNewUser implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("ghansham").password("hello").build()),
                Arguments.of(User.builder().userName("hema").password("").build())
        );
    }
}
