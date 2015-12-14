import static com.business.model.Defect.Type;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static com.business.model.Frill.Tier;
import static org.junit.Assert.assertThat;

import com.business.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


public class TestFrill {
    private Frill sut;

    @Before
    public void setUp() {
        Casing front = new Casing();
        front.addDefect(new Defect(Type.MINOR));
        front.addDefect(new Defect(Type.MAJOR));
        front.addDefect(new Defect(Type.MASSIVE));

        Casing rear = new Casing();

        Hinge hinge = new Hinge();
        hinge.addDefect(new Defect(Type.MINOR));
        hinge.addDefect(new Defect(Type.MASSIVE));

        Widget widget = new Widget();
        widget.addDefect(new Defect(Type.MINOR));
        List<Widget> widgets = new ArrayList<Widget>();
        widgets.add(widget);
        widget = new Widget();
        widget.addDefect(new Defect(Type.MINOR));
        widgets.add(widget);
        widget = new Widget();
        widget.addDefect(new Defect(Type.MAJOR));
        widgets.add(widget);
        widgets.add(new Widget());
        widgets.add(new Widget());

        sut = new Frill(widgets, front, rear, hinge);
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void testTierOne() {
        assertNotNull(sut.calculateTier());
        assertEquals(sut.calculateTier(), Tier.ONE);
    }

    @Test
    public void testTierIncompleteHinge() {
        sut.getHinge().addDefect(new Defect(Type.INCOMPLETE));
        assertThat(sut.calculateTier(), is(equalTo(Tier.INCOMPLETE)));
    }
    @Test
    public void testTierIncompleteFrontCasing() {
        Casing c = sut.getFrontCasing();
        c.addDefect(new Defect(Type.INCOMPLETE));
        assertThat(sut.calculateTier(), is(Tier.INCOMPLETE));
    }
    @Test
    public void testTierIncompleteRearCasing() {
        Casing c = sut.getRearCasing();
        c.addDefect(new Defect(Type.INCOMPLETE));
        assertThat(sut.calculateTier(), is(equalTo(Tier.INCOMPLETE)));
    }
    @Test
    public void testTierIncompleteWidget() {
        Widget w = sut.getWidgets().get(0);
        w.addDefect(new Defect(Type.INCOMPLETE));
        assertThat(sut.calculateTier(), is(equalTo(Tier.INCOMPLETE)));
    }
}
