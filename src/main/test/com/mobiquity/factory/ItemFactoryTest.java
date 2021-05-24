package com.mobiquity.factory;

import com.mobiquity.factory.ItemFactory;
import com.mobiquity.model.ItemDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ItemFactory.class)
@PowerMockIgnore("jdk.internal.reflect.*")
public class ItemFactoryTest {

    @Test
    public void createItem() {
        PowerMockito.mockStatic(ItemFactory.class);
        when(ItemFactory.createItem(any(), any(), any())).thenReturn(new ItemDTO(1,2.0, 3));
        ItemDTO itemTest = ItemFactory.createItem(5,1.9, 3);
        assertEquals(Integer.valueOf(1), itemTest.getId());
        assertEquals(Double.valueOf(2.0), itemTest.getWeight());
        assertEquals(Integer.valueOf(3), itemTest.getValue());
    }

}
