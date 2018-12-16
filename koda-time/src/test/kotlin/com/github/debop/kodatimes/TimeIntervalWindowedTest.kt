/*
 * Copyright (c) 2016. Sunghyouk Bae <sunghyouk.bae@gmail.com>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.debop.kodatimes

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue


class TimeIntervalWindowedTest: AbstractKodaTimesTest() {

    @Test
    fun `windowed year`() {
        val start = now().startOfYear()
        val endExclusive = start + 5.years()
        val interval = start..endExclusive
        logger.debug("interval=$interval")

        val windowed = interval.windowedYear(3, 2)
        windowed.forEach { items ->
            logger.debug("items = $items")
            assertTrue(items.first() in interval)
            assertTrue(items.last() in interval)
        }
        assertEquals(3, windowed.count())

        assertFailsWith(IllegalArgumentException::class) {
            interval.windowedYear(-1, 2)
        }
        assertFailsWith(IllegalArgumentException::class) {
            interval.windowedYear(2, -2)
        }
    }

    @Test
    fun `windowed month`() {
        val start = now().startOfMonth()
        val endExclusive = start + 5.months()
        val interval = start..endExclusive
        logger.debug("interval=$interval")

        val windowed = interval.windowedMonth(3, 2)
        windowed.forEach { items ->
            logger.debug("items = $items")
            assertTrue { items.all { it in interval } }
        }
        assertEquals(3, windowed.count())

        assertFailsWith(IllegalArgumentException::class) {
            interval.windowedMonth(-1, 2)
        }
        assertFailsWith(IllegalArgumentException::class) {
            interval.windowedMonth(2, -2)
        }
    }

    @Test
    fun `windowed week`() {
        val start = now().startOfWeek()
        val endExclusive = start + 5.weeks()
        val interval = start..endExclusive
        logger.debug("interval=$interval")

        val windowed = interval.windowedWeek(3, 2)
        windowed.forEach { items ->
            logger.debug("items = $items")
            assertTrue(items.all { it in interval })
        }
        assertEquals(3, windowed.count())

        assertFailsWith(IllegalArgumentException::class) {
            interval.windowedWeek(-1, 2)
        }
        assertFailsWith(IllegalArgumentException::class) {
            interval.windowedWeek(2, -2)
        }
    }

    @Test
    fun `windowed day`() {
        val start = now().startOfDay()
        val endExclusive = start + 5.days()
        val interval = start..endExclusive
        logger.debug("interval=$interval")

        val windowed = interval.windowedDay(3, 2)
        windowed.forEach { items ->
            logger.debug("items = $items")
            assertTrue(items.all { it in interval })
        }
        assertEquals(3, windowed.count())

        assertFailsWith(IllegalArgumentException::class) {
            interval.windowedDay(-1, 2)
        }
        assertFailsWith(IllegalArgumentException::class) {
            interval.windowedDay(2, -2)
        }
    }

    @Test
    fun `windowed hour`() {
        val start = now().trimToHour()
        val endExclusive = start + 5.hours()
        val interval = start..endExclusive
        logger.debug("interval=$interval")

        val windowed = interval.windowedHour(3, 2)
        windowed.forEach { items ->
            logger.debug("items = $items")
            assertTrue(items.all { it in interval })
        }
        assertEquals(3, windowed.count())

        assertFailsWith(IllegalArgumentException::class) {
            interval.windowedHour(-1, 2)
        }
        assertFailsWith(IllegalArgumentException::class) {
            interval.windowedHour(2, -2)
        }
    }

    @Test
    fun `windowed minute`() {
        val start = now().trimToMinute()
        val endExclusive = start + 5.minutes()
        val interval = start..endExclusive
        logger.debug("interval=$interval")

        val windowed = interval.windowedMinute(3, 2)
        windowed.forEach { items ->
            logger.debug("items = $items")
            assertTrue(items.all { it in interval })
        }
        assertEquals(3, windowed.count())

        assertFailsWith(IllegalArgumentException::class) {
            interval.windowedMinute(-1, 2)
        }
        assertFailsWith(IllegalArgumentException::class) {
            interval.windowedMinute(2, -2)
        }
    }

    @Test
    fun `windowed second`() {
        val start = now().trimToSecond()
        val endExclusive = start + 5.seconds()
        val interval = start..endExclusive
        logger.debug("interval=$interval")

        val windowed = interval.windowedSecond(3, 2)
        windowed.forEach { items ->
            logger.debug("items = $items")
            assertTrue(items.all { it in interval })
        }
        assertEquals(3, windowed.count())

        assertFailsWith(IllegalArgumentException::class) {
            interval.windowedSecond(-1, 2)
        }
        assertFailsWith(IllegalArgumentException::class) {
            interval.windowedSecond(2, -2)
        }
    }

    @Test
    fun `zipWithNext years`() {
        val start = now().startOfYear()
        val endExclusive = start + 5.years()
        val interval = start..endExclusive
        logger.debug("interval=$interval")

        val pairs = interval.zipWithNextYear().toList()
        assertEquals(4, pairs.size)

        pairs.forEach { (current, next) ->
            logger.debug("current=$current, next=$next")
            assertTrue { current in interval }
            assertTrue { next in interval }
        }
    }

    @Test
    fun `zipWithNext months`() {
        val start = now().startOfMonth()
        val endExclusive = start + 5.months()
        val interval = start..endExclusive
        logger.debug("interval=$interval")

        val pairs = interval.zipWithNextMonth().toList()
        assertEquals(4, pairs.size)

        pairs.forEach { (current, next) ->
            logger.debug("current=$current, next=$next")
            assertTrue { current in interval }
            assertTrue { next in interval }
        }
    }

    @Test
    fun `zipWithNext weeks`() {
        val start = now().startOfWeek()
        val endExclusive = start + 5.weeks()
        val interval = start..endExclusive
        logger.debug("interval=$interval")

        val pairs = interval.zipWithNextWeek().toList()
        assertEquals(4, pairs.size)

        pairs.forEach { (current, next) ->
            logger.debug("current=$current, next=$next")
            assertTrue { current in interval }
            assertTrue { next in interval }
        }
    }

    @Test
    fun `zipWithNext days`() {
        val start = now().startOfDay()
        val endExclusive = start + 5.days()
        val interval = start..endExclusive
        logger.debug("interval=$interval")

        val pairs = interval.zipWithNextDay().toList()
        assertEquals(4, pairs.size)

        pairs.forEach { (current, next) ->
            logger.debug("current=$current, next=$next")
            assertTrue { current in interval }
            assertTrue { next in interval }
        }
    }

    @Test
    fun `zipWithNext hours`() {
        val start = now().trimToHour()
        val endExclusive = start + 5.hours()
        val interval = start..endExclusive
        logger.debug("interval=$interval")

        val pairs = interval.zipWithNextHour().toList()
        assertEquals(4, pairs.size)

        pairs.forEach { (current, next) ->
            logger.debug("current=$current, next=$next")
            assertTrue { current in interval }
            assertTrue { next in interval }
        }
    }

    @Test
    fun `zipWithNext minutes`() {
        val start = now().trimToMinute()
        val endExclusive = start + 5.minutes()
        val interval = start..endExclusive
        logger.debug("interval=$interval")

        val pairs = interval.zipWithNextMinute().toList()
        assertEquals(4, pairs.size)

        pairs.forEach { (current, next) ->
            logger.debug("current=$current, next=$next")
            assertTrue { current in interval }
            assertTrue { next in interval }
        }
    }

    @Test
    fun `zipWithNext seconds`() {
        val start = now().trimToSecond()
        val endExclusive = start + 5.seconds()
        val interval = start..endExclusive
        logger.debug("interval=$interval")

        val pairs = interval.zipWithNextSecond().toList()
        assertEquals(4, pairs.size)

        pairs.forEach { (current, next) ->
            logger.debug("current=$current, next=$next")
            assertTrue { current in interval }
            assertTrue { next in interval }
        }
    }
}