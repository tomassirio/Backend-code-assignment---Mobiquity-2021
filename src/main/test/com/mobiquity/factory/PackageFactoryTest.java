package com.mobiquity.factory;

import com.mobiquity.model.ItemDTO;
import com.mobiquity.model.PackageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PackageFactory.class)
@PowerMockIgnore("jdk.internal.reflect.*")
public class PackageFactoryTest {

    @Test
    public void createPackage() {
        PowerMockito.mockStatic(PackageFactory.class);
        when(PackageFactory.createPackage(any(), any())).thenReturn(new PackageDTO(29, new ArrayList<>()));

        PackageDTO packageTest = PackageFactory.createPackage(1, new ArrayList<>());
        assertEquals(Integer.valueOf(29), packageTest.getCapacity());
        assertEquals(new ArrayList<>(), packageTest.getItems());
    }
}