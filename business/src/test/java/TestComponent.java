import static com.business.model.Defect.Type;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import com.business.model.Casing;
import com.business.model.Component;
import com.business.model.Defect;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestComponent {

    private Component sut;

    @Before
    public void setUp() {
        sut = new Casing();
        sut.addDefect(new Defect(Type.MINOR));
        sut.addDefect(new Defect(Type.MAJOR));
        sut.addDefect(new Defect(Type.MASSIVE));
    }
    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void testDefectValueForTier() {
        assertThat(sut.getDefectValueForTier(), is(not(equalTo(Type.MAJOR.getTypeValue()))));
        assertThat(sut.getDefectValueForTier(), is(equalTo(Type.MASSIVE.getTypeValue())));
    }

    @Test
    public void testForSingleDefectType() {
        sut.addDefect(new Defect(Type.MINOR));
        assertThat(sut.getDefects(), hasSize(4));
    }

    @Test
    public void testForIncomplete() {
        sut.addDefect(new Defect(Type.INCOMPLETE));
        assertThat(sut.getDefectValueForTier(), is(equalTo(Type.INCOMPLETE.getTypeValue())));
    }
}
