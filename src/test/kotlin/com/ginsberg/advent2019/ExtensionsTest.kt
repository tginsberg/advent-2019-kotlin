/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ExtensionsTest {

    @Nested
    @DisplayName("Flat Map Round Robin")
    inner class FlatMapRoundRobin {

        @Test
        @DisplayName("Lists have equal size")
        fun equalSized() {
            // Given
            val input = listOf(listOf("A", "C"), listOf("B", "D"))

            // When
            val output = input.flattenRoundRobin()

            // Then
            assertThat(output).isEqualTo(listOf("A", "B", "C", "D"))
        }

        @Test
        @DisplayName("Jagged lists")
        fun jaggedLists() {
            // Given
            val input = listOf(listOf("A", "C", "D"), listOf("B"))

            // When
            val output = input.flattenRoundRobin()

            // Then
            assertThat(output).isEqualTo(listOf("A", "B", "C", "D"))
        }

        @Test
        @DisplayName("With an empty list")
        fun withEmpty() {
            // Given
            val input = listOf(listOf("A", "C", "D"), listOf("B"), emptyList())

            // When
            val output = input.flattenRoundRobin()

            // Then
            assertThat(output).isEqualTo(listOf("A", "B", "C", "D"))
        }
    }
}