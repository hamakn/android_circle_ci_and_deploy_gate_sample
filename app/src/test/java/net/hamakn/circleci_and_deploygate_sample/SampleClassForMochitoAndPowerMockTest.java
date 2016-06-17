package net.hamakn.circleci_and_deploygate_sample;

import android.content.Context;
import android.util.Log;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(PowerMockRunner.class)
public class SampleClassForMochitoAndPowerMockTest extends TestCase {
    @Mock
    Context mMockContext;

    @Before
    public void setUp() {
        // mock Log
        PowerMockito.mockStatic(Log.class);
    }

    @Test
    @PrepareForTest({Log.class, SampleClassForMochitoAndPowerMock.class})
    public void mockInstance() throws Exception {
        // create mock instance of SampleClassForMochitoAndPowerMock
        SampleClassForMochitoAndPowerMock sample = Mockito.mock(SampleClassForMochitoAndPowerMock.class);

        // mock sample.flagByContext
        PowerMockito.when(sample.flagByContext()).thenReturn(true);

        assertThat(sample.flagByContext(), is(true));
    }

    @Test
    @PrepareForTest({Log.class, SampleClassForMochitoAndPowerMock.class})
    public void spyInstance() throws Exception {
        SampleClassForMochitoAndPowerMock sample = PowerMockito.spy(new SampleClassForMochitoAndPowerMock(mMockContext));

        // mock sample.flagByContext
        PowerMockito.when(sample.flagByContext()).thenReturn(true);

        assertThat(sample.logicWithFlag(), is(1));
    }

    @Test
    @PrepareForTest({Log.class, SampleClassForMochitoAndPowerMock.class})
    public void mockStatic() throws Exception {
        SampleClassForMochitoAndPowerMock sample = new SampleClassForMochitoAndPowerMock(mMockContext);

        // mock static
        PowerMockito.mockStatic(SampleClassForMochitoAndPowerMock.class);
        PowerMockito.doNothing().when(SampleClassForMochitoAndPowerMock.class, "staticWork1");
        PowerMockito.doReturn(42).when(SampleClassForMochitoAndPowerMock.class, "staticWork2");

        assertThat(sample.logicWithStatic(), is(42));
    }

    @Test
    @PrepareForTest({Log.class, SampleClassForMochitoAndPowerMock.class})
    public void throwError() throws Exception {
        SampleClassForMochitoAndPowerMock sample = PowerMockito.spy(new SampleClassForMochitoAndPowerMock(mMockContext));

        PowerMockito.when(sample.logicWithFlag()).thenThrow(new RuntimeException("by mock"));

        assertThat(sample.hasError(), is(true));
    }


    @Test
    @PrepareForTest({Log.class, SampleClassForMochitoAndPowerMock.class})
    public void callCount() throws Exception {
        SampleClassForMochitoAndPowerMock sample = PowerMockito.spy(new SampleClassForMochitoAndPowerMock(mMockContext));

        PowerMockito.when(sample.work1(Mockito.anyInt())).thenReturn(1);

        PowerMockito.mockStatic(SampleClassForMochitoAndPowerMock.class);
        PowerMockito.doNothing().when(SampleClassForMochitoAndPowerMock.class, "staticWork1");

        sample.doSomeWorks(1);

        // verify call count of instance
        Mockito.verify(sample, Mockito.times(3)).work1(1);

        // verify call count of static method by below 2 lines
        PowerMockito.verifyStatic(Mockito.times(2));
        SampleClassForMochitoAndPowerMock.staticWork1();
    }
}