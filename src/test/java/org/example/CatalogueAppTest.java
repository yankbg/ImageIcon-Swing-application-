package org.example;

import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.fixture.FrameFixture;

public class CatalogueAppTest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;

    @Override
    protected void onSetUp() {
        // Launch the GUI in a new thread
        CatalogueApp frame = new CatalogueApp();
        window = new FrameFixture(robot(), frame);
        window.show(); // Shows the frame to test
    }

    public void testSearchFieldExists() {
        window.textBox("searchField").requireVisible();
        window.textBox("searchField").requireEmpty();
    }

    public void testCardButtonClick() {
        window.button("View More").click();
        window.optionPane().requireVisible();
        window.optionPane().okButton().click(); // Close popup
    }
}
