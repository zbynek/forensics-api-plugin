package io.jenkins.plugins.forensics.miner;

import org.junit.jupiter.api.Test;

import static io.jenkins.plugins.forensics.assertions.Assertions.*;

/**
 * Tests the class {@link FileStatistics}.
 *
 * @author Ullrich Hafner
 */
class FileStatisticsTest {
    private static final String FILE = "file";
    private static final int ONE_DAY = 60 * 60 * 24;

    @Test
    void shouldCreateFileStatistics() {
        FileStatistics statistics = new FileStatistics(FILE, ONE_DAY * 10);

        assertThat(statistics).hasFileName(FILE);
        assertThat(statistics).hasNumberOfCommits(0);
        assertThat(statistics).hasAgeInDays(0);
        assertThat(statistics).hasLastModifiedInDays(0);
        assertThat(statistics).hasNumberOfAuthors(0);
        assertThat(statistics).hasLastModificationTime(0);
        assertThat(statistics).hasCreationTime(0);

        statistics.inspectCommit(ONE_DAY * 9, "one");
        assertThat(statistics).hasNumberOfCommits(1);
        assertThat(statistics).hasAgeInDays(1);
        assertThat(statistics).hasLastModifiedInDays(1);
        assertThat(statistics).hasNumberOfAuthors(1);
        assertThat(statistics).hasLastModificationTime(ONE_DAY * 9);
        assertThat(statistics).hasCreationTime(ONE_DAY * 9);

        statistics.inspectCommit(ONE_DAY * 8, "one");
        assertThat(statistics).hasNumberOfCommits(2);
        assertThat(statistics).hasAgeInDays(2);
        assertThat(statistics).hasLastModifiedInDays(1);
        assertThat(statistics).hasNumberOfAuthors(1);
        assertThat(statistics).hasLastModificationTime(ONE_DAY * 9);
        assertThat(statistics).hasCreationTime(ONE_DAY * 8);

        statistics.inspectCommit(ONE_DAY * 7, "two");
        assertThat(statistics).hasNumberOfCommits(3);
        assertThat(statistics).hasAgeInDays(3);
        assertThat(statistics).hasLastModifiedInDays(1);
        assertThat(statistics).hasNumberOfAuthors(2);
        assertThat(statistics).hasLastModificationTime(ONE_DAY * 9);
        assertThat(statistics).hasCreationTime(ONE_DAY * 7);

        statistics.inspectCommit(ONE_DAY * 7, "three");
        assertThat(statistics).hasNumberOfCommits(4);
        assertThat(statistics).hasAgeInDays(3);
        assertThat(statistics).hasLastModifiedInDays(1);
        assertThat(statistics).hasNumberOfAuthors(3);
        assertThat(statistics).hasLastModificationTime(ONE_DAY * 9);
        assertThat(statistics).hasCreationTime(ONE_DAY * 7);
    }

    @Test
    void shouldConvertWindowsName() {
        assertThat(new FileStatistics("C:\\path\\to\\file.txt")).hasFileName("C:/path/to/file.txt");
        assertThat(new FileStatistics("C:\\path\\to/file.txt")).hasFileName("C:/path/to/file.txt");
        assertThat(new FileStatistics("/path/to/file.txt")).hasFileName("/path/to/file.txt");
    }
}
