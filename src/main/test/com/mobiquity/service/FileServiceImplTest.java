package com.mobiquity.service;

import com.mobiquity.exception.APIException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileServiceImpl.class)
@PowerMockIgnore("jdk.internal.reflect.*")
public class FileServiceImplTest {

    @Mock
    FileServiceImpl fileService;

    @Test
    public void openFileHappyPath() throws APIException {
        when(fileService.openFile(any())).thenReturn(new File("src/main/test/resources/test_file"));
        assertEquals(fileService.openFile("Test"), new File("src/main/test/resources/test_file"));
    }

    @Test(expected = APIException.class)
    public void openFileThrowsWrongPathException() throws APIException {
        doThrow(APIException.class)
                .when(fileService)
                .openFile(anyString());
        fileService.openFile(anyString());
    }

    @Test(expected = IOException.class)
    public void openFileThrowsWrongFormatException() throws APIException {
        doThrow(IOException.class)
                .when(fileService)
                .openFile(anyString());
        fileService.openFile(anyString());
    }

    @Test
    public void writeToFileHappyPath() {
        File file = new File("src/main/test/resources/test_file");

        try {
            doNothing().when(fileService).writeToPath(file.getPath(), "Lorem Ipsum");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                assertEquals(scanner.nextLine(), "Lorem Ipsum");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = APIException.class)
    public void writeToFileWhichDoesntExist() throws APIException {
        File file = new File("sample");
        try {
            doNothing().when(fileService).writeToPath(file.getPath(), "Lorem Ipsum");
            Scanner scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new APIException("File was not found", e);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
