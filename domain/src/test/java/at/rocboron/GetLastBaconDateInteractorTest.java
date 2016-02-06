package at.rocboron;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import at.rocboron.datastore.BaconDatastore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class GetLastBaconDateInteractorTest {

    @Mock
    BaconDatastore datastore;

    GetLastBaconDateInteractor getLastBaconDateInteractor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getLastBaconDateInteractor = new GetLastBaconDateInteractor(datastore);
    }

    @Test
    public void shouldReturnToday_whenNothingWasStored() throws Exception {
        when(datastore.getLastBaconDate()).thenReturn(null);

        Date lastBaconDay = getLastBaconDateInteractor.getLastBaconDate();

        assertThat(lastBaconDay).isCloseTo(new Date(), TimeUnit.SECONDS.toMillis(1));
    }

    @Test
    public void shouldReturnRocsBirthday_whenRocsBirthdayWasStored() throws Exception {
        when(datastore.getLastBaconDate()).thenReturn(rocsBirthday());

        Date lastBaconDay = getLastBaconDateInteractor.getLastBaconDate();

        assertThat(lastBaconDay).isEqualTo(rocsBirthday());
    }

    private Date rocsBirthday() throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse("23/04/1986");
    }
}