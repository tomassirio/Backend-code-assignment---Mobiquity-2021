package com.mobiquity.service;

import com.mobiquity.exception.APIException;
import com.mobiquity.factory.ItemFactory;
import com.mobiquity.factory.PackageFactory;
import com.mobiquity.model.PackageDTO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ParseServiceImpl.class)
@PowerMockIgnore("jdk.internal.reflect.*")
public class ParseServiceImplTest {

    @Mock
    ParseServiceImpl parseService;

    File file;
    List<PackageDTO> packages;


    @Before
    public void init() {
        file = new File("src/main/test/resources/example_input");
        packages = Collections.EMPTY_LIST;
        Collections.addAll(packages = new ArrayList<PackageDTO>(),
                PackageFactory.createPackage(81,
                        Arrays.asList(
                                ItemFactory.createItem(1,53.38,45),
                                ItemFactory.createItem(2,88.62,98),
                                ItemFactory.createItem(3,78.48,3),
                                ItemFactory.createItem(4,72.30,76),
                                ItemFactory.createItem(5,30.18,9),
                                ItemFactory.createItem(6,46.34,48))
                        ),
                PackageFactory.createPackage(8,
                        Arrays.asList(
                                ItemFactory.createItem(1,15.3,34))
                        ),
                PackageFactory.createPackage(75,
                        Arrays.asList(
                                ItemFactory.createItem(1,85.31,29),
                                ItemFactory.createItem(2,14.55,74),
                                ItemFactory.createItem(3,3.98,16),
                                ItemFactory.createItem(4,26.24,55),
                                ItemFactory.createItem(5,63.69,52),
                                ItemFactory.createItem(6,76.25,75),
                                ItemFactory.createItem(7,60.02,74))
                ),
                PackageFactory.createPackage(56,
                        Arrays.asList(
                                ItemFactory.createItem(1,90.72,13),
                                ItemFactory.createItem(2,33.80,40),
                                ItemFactory.createItem(3,43.15,10),
                                ItemFactory.createItem(4,37.97,16),
                                ItemFactory.createItem(5,46.81,36),
                                ItemFactory.createItem(6,48.77,79),
                                ItemFactory.createItem(7,81.80,45),
                                ItemFactory.createItem(8,19.36,79),
                                ItemFactory.createItem(9,6.76,64))
                )
        );
    }

    @Test
    @Ignore
    public void parseFileHappyPath() {
        ValidatorFactory validatorFactory = PowerMockito.mock(ValidatorFactory.class);
        Validator validator = PowerMockito.mock(Validator.class);
        try{
            PowerMockito.whenNew(ValidatorFactory.class).withNoArguments().thenReturn(Validation.buildDefaultValidatorFactory());
            PowerMockito.whenNew(Validator.class).withNoArguments().thenReturn(validatorFactory.getValidator());
            when(parseService.parseFile(file)).thenReturn(packages);

            assertEquals(parseService.parseFile(file), packages);
        }catch (APIException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
