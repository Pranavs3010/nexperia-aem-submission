// File: core/src/main/java/com/adobe/aem/guides/wknd/core/models/impl/AdvancedBlogListModelImpl.java
package com.adobe.aem.guides.wknd.core.models.impl;

import com.adobe.aem.guides.wknd.core.models.AdvancedBlogListModel; // This import should now work!
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
    adaptables = SlingHttpServletRequest.class,
    adapters = AdvancedBlogListModel.class, // It provides the AdvancedBlogListModel interface
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class AdvancedBlogListModelImpl implements AdvancedBlogListModel { // It implements the interface

    @ValueMapValue
    private String title;

    @Override // This annotation now works because the interface is found
    public String getTitle() {
        return title != null ? title : "Our Latest Blog Posts";
    }
}