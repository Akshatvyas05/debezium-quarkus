/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.quarkus.debezium.deployment.engine;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class GeneratedIdentifierTest {

    @Test
    void shouldKeepAllUuidCharacters() {
        UUID id = UUID.fromString("abcdefab-1234-5678-9abc-def012345678");

        assertThat(GeneratedIdentifier.of(id)).isEqualTo("abcdefab123456789abcdef012345678");
    }

    @Test
    void shouldNotBeEmptyWhenFirstSegmentHasNoDigits() {
        UUID id = UUID.fromString("abcdefab-0000-0000-0000-000000000000");

        assertThat(GeneratedIdentifier.of(id)).isNotEmpty();
    }

    @Test
    void shouldBeUnique() {
        Set<String> ids = new HashSet<>();
        for (int i = 0; i < 100_000; i++) {
            ids.add(GeneratedIdentifier.of(UUID.randomUUID()));
        }

        assertThat(ids).hasSize(100_000);
    }
}
