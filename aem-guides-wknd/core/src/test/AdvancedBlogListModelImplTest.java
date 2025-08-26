package com.adobe.aem.guides.wknd.core.models.impl;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.adobe.aem.guides.wknd.core.models.AdvancedBlogListModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(AemContextExtension.class)
class AdvancedBlogListModelImplTest {

    private static final String RESOURCE_PATH = "/content/mypage/mycomponent";
    private static final String NULL_RESOURCE_PATH = "/content/mypage/nullcomponent";
    private static final String TITLE = "Test Title";
    private static final String DEFAULT_TITLE = "Our Latest Blog Posts";

    private final AemContext ctx = new AemContext();
    private AdvancedBlogListModel model;

    @BeforeEach
    void setUp() {
        ctx.create().resource(RESOURCE_PATH, "title", TITLE);
        ctx.currentResource(RESOURCE_PATH);
        model = ctx.currentResource().adaptTo(AdvancedBlogListModel.class);
    }

    @Test
    void getTitle_shouldReturnConfiguredTitle() {
        String actual = model.getTitle();
        assertEquals(TITLE, actual, "The title should match the property set in the mock resource.");
    }

    @Test
    void getTitle_shouldReturnDefaultWhenTitleIsMissing() {
        ctx.currentResource(ctx.create().resource(NULL_RESOURCE_PATH));
        model = ctx.currentResource().adaptTo(AdvancedBlogListModel.class);

        String actual = model.getTitle();
        assertEquals(DEFAULT_TITLE, actual, "Should return the default title when the property is not set.");
    }

    @Test
    void getTitle_shouldReturnNullWhenResourceIsNull() {
        ctx.currentResource(null);
        model = ctx.currentResource() != null ? ctx.currentResource().adaptTo(AdvancedBlogListModel.class) : null;

        assertNull(model, "Model should be null when no resource is set.");
    }
}