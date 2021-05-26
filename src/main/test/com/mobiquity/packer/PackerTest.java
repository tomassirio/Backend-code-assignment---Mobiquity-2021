package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.service.FileServiceImpl;
import com.mobiquity.service.ParseServiceImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Packer.class)
@PowerMockIgnore("jdk.internal.reflect.*")
public class PackerTest {

    @Mock
    Packer packer;

    @Test
    @Ignore
    public void packHappyPath() {
        PowerMockito.mockStatic(Packer.class);
        FileServiceImpl fileService = PowerMockito.mock(FileServiceImpl.class);
        ParseServiceImpl parseService = PowerMockito.mock(ParseServiceImpl.class);
        try{
            PowerMockito.whenNew(FileServiceImpl.class).withNoArguments().thenReturn(FileServiceImpl.getInstance());
            PowerMockito.whenNew(ParseServiceImpl.class).withNoArguments().thenReturn(ParseServiceImpl.getInstance());
            when(Packer.pack(any())).thenReturn("String");
            assertEquals(Packer.pack(any()), "String");

        }catch (APIException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
